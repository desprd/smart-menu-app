package com.ilyaproject.smart_menu_server.repository;

import com.ilyaproject.smart_menu_server.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
}
