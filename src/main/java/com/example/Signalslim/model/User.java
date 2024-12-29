package com.example.Signalslim.model;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Spring Security Method: Retrieve Authorities (roles)
    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // No roles in this simplified version
    }

    // Spring Security Methods: Account Status
    @Override
    public boolean isAccountNonExpired() {
        return true; // Always valid for this example
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Always unlocked for this example
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Always valid for this example
    }

    @Override
    public boolean isEnabled() {
        return true; // Always enabled for this example
    }
}
