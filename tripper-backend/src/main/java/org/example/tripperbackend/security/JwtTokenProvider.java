package org.example.tripperbackend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    private final SecretKey jwtSecret;
    private final int jwtExpirationMs;

//    public JwtTokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") int expirationMs) {
//        // Decode the base64 secret key
//        byte[] decodedKey = Base64.getDecoder().decode(secret);
//        this.jwtSecret = Keys.hmacShaKeyFor(decodedKey);
//        this.jwtExpirationMs = expirationMs;
//    }


    public JwtTokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") int expirationMs) {

        // Generate a strong key using the io.jsonwebtoken.security.Keys class
        /*
            Storing and Managing the Key
            Secure Storage: In a production environment, you should store this key securely, such as in an environment variable, or using a secret management system. Do not hard-code keys in your source code.
            Consistency: Ensure that the same key is used for signing and validating the JWT tokens. Changes in the key will invalidate existing tokens.
         */
        this.jwtSecret =  Keys.secretKeyFor(SignatureAlgorithm.HS512);
        this.jwtExpirationMs = expirationMs;
    }

    // Generate token for user
    public String generateToken(Authentication authentication) {
        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userPrincipal.getUserId());

        return Jwts.builder()
                .setClaims(claims)  // Set the custom claim
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(jwtSecret, SignatureAlgorithm.HS512)
                .compact();
    }

    // Validate the JWT Token
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (JwtException ex) {
            throw new IllegalArgumentException("Invalid JWT: " + ex.getMessage());
        }
    }

    // Get username from the JWT token
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

}