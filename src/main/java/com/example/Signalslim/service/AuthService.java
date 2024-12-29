package com.example.Signalslim.service;

import com.example.Signalslim.dto.UserDTO;
import com.example.Signalslim.mapper.UserMapper;
import com.example.Signalslim.model.User;
import com.example.Signalslim.repository.UserRepository;
import com.example.Signalslim.security.JwtUtils;
import com.example.Signalslim.security.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.Signalslim.security.JwtTokenProvider;

import java.util.Optional;



@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserMapper userMapper;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    // Gérer l'inscription d'un utilisateur
    public String signUp(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return "Email déjà utilisé";
        }

        User user = userMapper.userDTOToUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Hacher le mot de passe

        userRepository.save(user);
        return "Inscription réussie";
    }

    // Authentifier un utilisateur
    public boolean login(String email, String password) {
        // Vérifier si l'utilisateur existe
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null) {
            // Vérifier si le mot de passe est correct
            if (passwordEncoder.matches(password, user.getPassword())) {
                return true; // Connexion réussie
            }
        }
        return false; // Connexion échouée
    }

    // Déconnexion simplifiée (supprimer simplement la session côté client)
    public String logout(String email, String password) {
        // Vérifier les informations de l'utilisateur avant de "déconnecter"
        boolean isAuthenticated = login(email, password);
        if (isAuthenticated) {
            // Logique de déconnexion (ici, on suppose que la déconnexion est juste une fin de session)
            return "Déconnexion réussie";
        }
        return "Email ou mot de passe incorrect";
    }

    // Générer un token JWT
    public String generateToken(User user) {
        Authentication authentication = new CustomAuthentication(user);
        return jwtUtils.generateJwtToken(authentication);
    }

    // Valider un token JWT
    public boolean validateToken(String token) {
        return jwtUtils.validateJwtToken(token);
    }

    // Mettre à jour les informations utilisateur
    public String updateUserInfo(Long userId, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());

            // Vous pouvez ajouter des validations supplémentaires ici

            userRepository.save(user);
            return "Informations utilisateur mises à jour avec succès";
        }
        return "Utilisateur non trouvé";
    }

    // Envoyer un lien ou code de réinitialisation de mot de passe
    public String resetPassword(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            // Logique d'envoi d'un lien ou code pour la réinitialisation
            return "Code de réinitialisation envoyé à votre email";
        }
        return "Utilisateur non trouvé";
    }

    // Réinitialiser le mot de passe avec un token valide
    public String confirmResetPassword(String token, String newPassword) {
        if (jwtUtils.validateJwtToken(token)) {
            // Logique pour réinitialiser le mot de passe
            String username = jwtUtils.getUserNameFromJwtToken(token);
            Optional<User> userOptional = userRepository.findByUsername(username);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setPassword(passwordEncoder.encode(newPassword)); // Hacher le nouveau mot de passe
                userRepository.save(user);
                return "Mot de passe réinitialisé avec succès";
            }
            return "Utilisateur non trouvé";
        }
        return "Token invalide";
    }
    // Récupérer le profil de l'utilisateur
    public UserDTO getUserProfile(String email, String password) {
        // Vérifier si l'utilisateur existe
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Retourner les informations de l'utilisateur si le mot de passe est correct
            return userMapper.userToUserDTO(user);
        }
        return null;  // Si l'utilisateur n'existe pas ou le mot de passe est incorrect
    }
}
