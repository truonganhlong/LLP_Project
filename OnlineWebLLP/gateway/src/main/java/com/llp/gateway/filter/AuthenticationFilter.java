package com.llp.gateway.filter;

import com.llp.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
        public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // Check if authentication is required for the current API
            if (validator.isSecured.test(request)) {
                // Check if the current API allows unauthenticated access
                if (!config.isAuthenticationRequired(request)) {
                    return chain.filter(exchange); // Allow unauthenticated access
                }

                // Check for the presence of the Authorization header
                if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing authorization header");
                }

                String authHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                try {
                    // Extract claims and validate roles based on the current API
                    List<String> requiredRoles = config.getRolesForAPI(request);
                    jwtUtil.validateRoles(authHeader, requiredRoles);

                    // Add custom headers or user information as needed
                    request = exchange.getRequest()
                            .mutate()
                            .header("loggedInUser", jwtUtil.extractUsername(authHeader))
                            .build();
                } catch (Exception e) {
                    throw new RuntimeException("Unauthorized access to application");
                }
            }

            return chain.filter(exchange.mutate().request(request).build());
        });
    }
    public static class Config {
        private final List<String> PUBLIC_API_PATHS = Arrays.asList(
                "/api/common/intro/public",
                "/api/course/category/public",
                "/api/course/course/public",
                "/api/course/discount/public",
                "/api/course/faqTopic/public",
                "/api/course/language/public",
                "/api/course/lecture/public",
                "/api/course/level/public",
                "/api/course/prominentTopic/public",
                "/api/course/review/public",
                "/api/course/section/public",
                "/api/course/specialized/public",
                "/api/course/subCategory/public",
                "/api/course/tag/public",
                "/api/course/topic/public",
                "/api/order/paymentMethod/public",
                "/api/order/order/public",
                "/api/user/user/public"
        );
        private final List<String> ADMIN_API_PATHS = Arrays.asList(
                "/api/common/intro/admin",
                "/api/course/category/admin",
                "/api/course/course/admin",
                "/api/course/discount/admin",
                "/api/course/faqTopic/admin",
                "/api/course/language/admin",
                "/api/course/lecture/admin",
                "/api/course/level/admin",
                "/api/course/prominentTopic/admin",
                "/api/course/review/admin",
                "/api/course/section/admin",
                "/api/course/specialized/admin",
                "/api/course/subCategory/admin",
                "/api/course/tag/admin",
                "/api/course/topic/admin",
                "/api/order/paymentMethod/admin",
                "/api/order/order/admin",
                "/api/user/user/admin"
        );
        private final List<String> USER_API_PATHS = Arrays.asList(
                "/api/common/intro/user",
                "/api/course/category/user",
                "/api/course/course/user",
                "/api/course/discount/user",
                "/api/course/faqTopic/user",
                "/api/course/language/user",
                "/api/course/lecture/user",
                "/api/course/level/user",
                "/api/course/prominentTopic/user",
                "/api/course/review/user",
                "/api/course/section/user",
                "/api/course/specialized/user",
                "/api/course/subCategory/user",
                "/api/course/tag/user",
                "/api/course/topic/user",
                "/api/order/paymentMethod/user",
                "/api/order/order/user",
                "/api/user/user/user"
        );
        private final List<String> TEACHER_API_PATHS = Arrays.asList(
                "/api/common/intro/teacher",
                "/api/course/category/teacher",
                "/api/course/course/teacher",
                "/api/course/discount/teacher",
                "/api/course/faqTopic/teacher",
                "/api/course/language/teacher",
                "/api/course/lecture/teacher",
                "/api/course/level/teacher",
                "/api/course/prominentTopic/teacher",
                "/api/course/review/teacher",
                "/api/course/section/teacher",
                "/api/course/specialized/teacher",
                "/api/course/subCategory/teacher",
                "/api/course/tag/teacher",
                "/api/course/topic/teacher",
                "/api/order/paymentMethod/teacher",
                "/api/order/order/teacher",
                "/api/user/user/teacher"
        );

        public boolean isAuthenticationRequired(ServerHttpRequest request) {
            String path = request.getPath().toString();

            // Check if the API path requires authentication
            return !PUBLIC_API_PATHS.stream().anyMatch(path::startsWith);
        }
        public List<String> getRolesForAPI(ServerHttpRequest request) {
            String path = request.getPath().toString();

            // Assign roles based on the API path
            if (ADMIN_API_PATHS.stream().anyMatch(path::startsWith)) {
                return Arrays.asList("ROLE_ADMIN");
            }
            if (USER_API_PATHS.stream().anyMatch(path::startsWith)){
                return Arrays.asList("ROLE_USER");
            }
            if (TEACHER_API_PATHS.stream().anyMatch(path::startsWith)) {
                return Arrays.asList("ROLE_TEACHER");
            }
            return Collections.emptyList();
        }
    }
}






