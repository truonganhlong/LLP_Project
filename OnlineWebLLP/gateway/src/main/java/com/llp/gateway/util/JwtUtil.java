package com.llp.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolve){
        final Claims claims = extractAllClaim(token);
        return claimsResolve.apply(claims);
    }

    public Claims extractAllClaim(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void validateRoles(String token, List<String> requiredRoles) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);

        Claims claims = claimsJws.getBody();

        // Extract roles from the JWT claims
        // Extract roles from the JWT claims
        List<String> userRoles = (List<String>) claims.get("roles", List.class)
                .stream()
                .map(roleMap -> {
                    if (roleMap instanceof LinkedHashMap) {
                        return (String) ((LinkedHashMap<?, ?>) roleMap).get("authority");
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // Check if the user has at least one of the required roles
        boolean hasCommonRole = false;
        for (String requiredRole : requiredRoles) {
            if (userRoles.contains(requiredRole)) {
                hasCommonRole = true;
                break;
            }
        }

        if (!hasCommonRole) {
            throw new RuntimeException("Insufficient permissions to access this resource");
        }

        // Optional: Check for token expiration (you can customize this part based on your needs)
        if (claims.getExpiration() != null && claims.getExpiration().before(new Date())) {
            throw new RuntimeException("Token has expired");
        }
    }
}
