package com.llp.userservice.services;

public interface JwtService {
    String extractUsername(String token);
}
