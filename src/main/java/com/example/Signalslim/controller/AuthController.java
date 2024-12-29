package com.example.Signalslim.controller;

import com.example.Signalslim.dto.UserDTO;
import com.example.Signalslim.model.LoginRequest;
import com.example.Signalslim.service.AuthService;
import com.example.Signalslim.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtUtils;

    // Méthode d'inscription (POST)
    @PostMapping(value = "/signup", produces = { "application/json", "application/xml" })
    public ResponseEntity<String> signUp(@RequestBody UserDTO userDTO) {
        String result = authService.signUp(userDTO);
        if ("Inscription réussie".equals(result)) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    // Méthode de connexion (POST)
    @PostMapping(value = "/login", produces = { "application/json", "application/xml" })
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            boolean isAuthenticated = authService.login(loginRequest.getEmail(), loginRequest.getPassword());

            if (isAuthenticated) {
                return ResponseEntity.ok("Connexion réussie !");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe incorrect.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue.");
        }
    }

    @PostMapping(value = "/logout", produces = { "application/json" })
    public ResponseEntity<String> logout(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String password = requestBody.get("password");

        // Appeler le service pour effectuer la déconnexion
        String result = authService.logout(email, password);

        if ("Déconnexion réussie".equals(result)) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    @PostMapping(value = "/profile", produces = { "application/json" })
    public ResponseEntity<UserDTO> getProfile(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String password = requestBody.get("password");

        // Appeler le service pour récupérer le profil de l'utilisateur
        UserDTO userDTO = authService.getUserProfile(email, password);

        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);  // Email ou mot de passe incorrect
    }
    @PutMapping(value = "/update/{userId}", produces = { "application/json", "application/xml" })
    public ResponseEntity<String> updateUserInfo(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        String result = authService.updateUserInfo(userId, userDTO);
        if ("Informations utilisateur mises à jour avec succès".equals(result)) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

}
