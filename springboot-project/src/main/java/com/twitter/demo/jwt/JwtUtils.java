package com.twitter.demo.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {


    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        public String generateToken(UUID userId, String email) {
            return Jwts.builder()
                    .setSubject(email)
                    .claim("id", userId.toString())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10h
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
        }

        public String extractEmail(String token) {
            return Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }

        public boolean isTokenValid(String token) {
            try {
                token = token.replace("Bearer ", "").trim();
                Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
                return true;
            } catch (DecodingException e) {
                System.err.println("Error de decodificación: " + e.getMessage());
                System.err.println("Token recibido: " + token); // Debug
            } catch (JwtException e) {
                System.err.println("Error JWT: " + e.getMessage());
            }
            return false;
        }
    }

