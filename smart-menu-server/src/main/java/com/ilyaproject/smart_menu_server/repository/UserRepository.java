package com.ilyaproject.smart_menu_server.repository;

import com.ilyaproject.smart_menu_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
