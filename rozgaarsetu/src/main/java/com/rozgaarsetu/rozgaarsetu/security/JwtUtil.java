//package com.rozgaarsetu.rozgaarsetu.security;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//
//    private final String SECRET = "this_is_a_very_secure_secret_key_for_jwt_123456";
//
//    private Key getKey() {
//        return Keys.hmacShaKeyFor(SECRET.getBytes());
//    }
//
//    public String generateToken(Long userId, String role) {
//        return Jwts.builder()
//                .setSubject(String.valueOf(userId))
//                .claim("role", role)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
//                .signWith(getKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public Claims extractClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public Long extractUserId(String token) {
//        return Long.parseLong(extractClaims(token).getSubject());
//    }
//
//    public String extractRole(String token) {
//        return extractClaims(token).get("role", String.class);
//    }
//}

package com.rozgaarsetu.rozgaarsetu.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 🔐 Strong secret key (must be >= 32 characters)
    private static final String SECRET = "this_is_a_very_secure_secret_key_for_jwt_12345678901234567890";

    // ⏳ Token validity (10 hours)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // 🔹 Generate JWT Token
    public String generateToken(Long userId, String role) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔹 Extract all claims
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 🔹 Extract userId
    public Long extractUserId(String token) {
        return Long.parseLong(extractClaims(token).getSubject());
    }

    // 🔹 Extract role
    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    // 🔹 Validate token
    public boolean isTokenValid(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}