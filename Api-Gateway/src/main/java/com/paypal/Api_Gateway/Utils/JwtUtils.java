package com.paypal.Api_Gateway.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Date;
import java.util.Map;

public class JwtUtils {

    private static final String SECRET_KEY = "A1b2C3d4E5f6G7h8I9j0K1l2M3n4O5p6";

    private static Key getSigningKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256");
    }

    public static Claims validateToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
