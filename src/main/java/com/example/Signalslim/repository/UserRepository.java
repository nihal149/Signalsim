package com.example.Signalslim.repository;


import com.example.Signalslim.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Trouver un utilisateur par nom d'utilisateur
    Optional<User> findByUsername(String username);
    public boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);

    // Vous pouvez ajouter d'autres méthodes spécifiques si nécessaire
}
