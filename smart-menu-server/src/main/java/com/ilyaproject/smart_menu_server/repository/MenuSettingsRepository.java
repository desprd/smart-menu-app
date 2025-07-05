package com.ilyaproject.smart_menu_server.repository;

import com.ilyaproject.smart_menu_server.model.MenuSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuSettingsRepository extends JpaRepository<MenuSettings, Integer> {
}
