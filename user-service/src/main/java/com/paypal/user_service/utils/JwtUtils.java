package com.paypal.user_service.utils;

import java.security.Key;
import java.security.Signature;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {


        private static final String SECRET_KEY = "A1b2C3d4E5f6G7h8I9j0K1l2M3n4O5p6";

        private Key getSigningKey() {
            return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        }

        // ✅ Generate Token
        public String generateToken(Map<String, Object> claims, String email) {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(email)
                    .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                    .setExpiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();
        }

        // ✅ Extract all claims
        private Claims extractAllClaims(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }

        // ✅ Extract username (email)
        public String extractUsername(String token) {
            return extractAllClaims(token).getSubject();
        }

        // ✅ Extract role
        public String extractRole(String token) {
            return extractAllClaims(token).get("role", String.class);
        }

        // ✅ Extract expiration
        public Date extractExpiration(String token) {
            return extractAllClaims(token).getExpiration();
        }

        // ✅ Check expiry
        private boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new java.util.Date());
        }

        // ✅ Validate token properly
        public boolean validateToken(String token, String username) {
            final String extractedUsername = extractUsername(token);
            return (extractedUsername.equals(username) && !isTokenExpired(token));
        }
    }

