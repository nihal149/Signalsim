package com.example.Signalslim.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.Signalslim.model.User;

import java.util.Collection;

public class CustomAuthentication implements Authentication {

    private final User user;

    public CustomAuthentication(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();  // Utilisation des autorités de l'utilisateur
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();  // Retourne le mot de passe de l'utilisateur
    }

    @Override
    public Object getDetails() {
        return user;  // Donne l'utilisateur comme détails
    }

    @Override
    public Object getPrincipal() {
        return user;  // Utilise l'objet User comme principal
    }

    @Override
    public boolean isAuthenticated() {
        return true;  // Indique que l'utilisateur est authentifié
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        // Implémentation non nécessaire dans ce cas
    }

    @Override
    public String getName() {
        return user.getUsername();  // Retourne le nom de l'utilisateur
    }
}
