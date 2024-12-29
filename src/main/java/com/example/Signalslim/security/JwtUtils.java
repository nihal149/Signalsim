package com.example.Signalslim.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    private Key key;

    // Initialisation de la clé secrète après injection des propriétés
    @PostConstruct
    public void init() {
        if (jwtSecret == null || jwtSecret.isEmpty()) {
            throw new IllegalArgumentException("JWT Secret cannot be null or empty");
        }
        // Vérification que la clé est assez longue
        if (jwtSecret.length() < 32) {
            throw new IllegalArgumentException("JWT Secret must be at least 32 characters long for HMAC-SHA512");
        }
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // Méthode pour générer le token JWT
    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())  // Définit le nom d'utilisateur dans le token
                .setIssuedAt(new Date())  // Définit la date de création du token
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))  // Définit la date d'expiration du token
                .signWith(key, SignatureAlgorithm.HS512)  // Signature du token avec la clé secrète
                .compact();  // Construit le token JWT
    }

    // Méthode pour extraire le nom d'utilisateur du token JWT
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Méthode pour valider le token JWT
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(authToken);
            return true;  // Le token est valide
        } catch (SecurityException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            // Log pour faciliter le débogage
            System.err.println("Invalid JWT Token: " + e.getMessage());
            return false;  // Le token n'est pas valide
        }
    }
}
