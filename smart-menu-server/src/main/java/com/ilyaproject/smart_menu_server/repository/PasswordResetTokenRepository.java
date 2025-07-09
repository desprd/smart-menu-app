package com.ilyaproject.smart_menu_server.repository;

import com.ilyaproject.smart_menu_server.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    Optional<PasswordResetToken> findByToken(String token);
    PasswordResetToken findByUserId(Integer id);
}
