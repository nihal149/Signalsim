package com.example.Signalslim.security;

import com.example.Signalslim.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY = "yourSecretKey12345"; // Clé secrète pour signer le token
    private final long EXPIRATION_TIME = 86400000; // Durée de validité du token (1 jour en millisecondes)

    // Méthode pour générer un token JWT
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail()) // Utiliser l'email de l'utilisateur comme sujet
                .claim("username", user.getUsername()) // Ajouter des informations supplémentaires (optionnel)
                .claim("id", user.getId()) // Ajouter l'ID de l'utilisateur (optionnel)
                .setIssuedAt(new Date()) // Date de génération du token
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Date d'expiration
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // Algorithme et clé secrète
                .compact();
    }
}

