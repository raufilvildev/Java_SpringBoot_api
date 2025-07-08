package com.mi_app.api.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mi_app.api.exception.InvalidTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

  @Value("${jwt.secret-key}")
  private String secretKeyString;

  @Value("${jwt.expiration-ms}")
  private long expirationTimeMs;

  private SecretKey secretKey;

  private JwtParser jwtParser;

  @PostConstruct
  public void init() {
    this.secretKey = Keys
        .hmacShaKeyFor(secretKeyString.getBytes());
    this.jwtParser = Jwts.parser().verifyWith(secretKey).build();
  }

  public String generateToken(String uuid) {
    return "Bearer " + Jwts.builder()
        .subject(uuid)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + expirationTimeMs))
        .signWith(secretKey)
        .compact();
  }

  public String extractUuid(String token) {
    return validateToken(token).getPayload().getSubject();
  }

  public Jws<Claims> validateToken(String token) {
    try {
      return jwtParser.parseSignedClaims(token);
    } catch (JwtException | IllegalArgumentException e) {
      throw new InvalidTokenException("Error al validar el token.");
    }
  }
}
