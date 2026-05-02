package com.paypal.user_service.utils;

import java.security.Key;
import java.security.Signature;
import java.sql.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
   private static final String SECRET_KEY = "A1b2C3d4E5f6G7h8I9j0K1l2M3n4O5p6";


    // simplelly key conversion into signed key
    private Key getSigningKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256");
    }

    // generating jwt token
    public String generateToken(Map<String, Object> claims,String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigningKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    /// email id and role of that
    

    //extracting email from jwt token
    public String extractEmail(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //extracting username from jwt token || imageing. username ==email
    public String extractUsername(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // extracting role from jwt token
    public String extractRole(String token){
        return (String) Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }


    // validating jwt token 
    public boolean validateToken(String token,String username) {
        try {
            extractEmail(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }  
    
}
