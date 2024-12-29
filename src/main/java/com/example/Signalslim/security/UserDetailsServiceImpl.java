package com.example.Signalslim.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // Vous pouvez remplacer cette méthode par un appel à votre base de données pour récupérer un utilisateur
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Ici, nous simulons un utilisateur simple avec un mot de passe "password" et un rôle "USER"
        if ("user".equals(username)) {
            return new User("user", "$2a$10$7XhLwXz77sM8L0u7yX9hPu.xGZBfvevh2Dd8gQXbJXJxRYDfrtD5G", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
