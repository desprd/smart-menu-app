package com.ilyaproject.smart_menu_server.service;

import com.ilyaproject.smart_menu_server.config.details.CustomUserDetails;
import com.ilyaproject.smart_menu_server.dto.profile.MenuSettingsRequestDTO;
import com.ilyaproject.smart_menu_server.exception.ProfileException;
import com.ilyaproject.smart_menu_server.model.User;
import com.ilyaproject.smart_menu_server.repository.UserRepository;
import com.ilyaproject.smart_menu_server.utils.UserUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuSettingsService {
    private final UserRepository repository;
    private final UserUtils utils;
    @Transactional
    @CacheEvict(cacheNames = "settings", key = "#authentication.name")
    public void updateMenuService(@RequestBody @Valid MenuSettingsRequestDTO req,
                                  Authentication authentication) throws Exception{
        User user = utils.getUserByAuthentication(authentication);
        try {
            var menuSettings = user.getMenuSettings();
            menuSettings.setIsVegetarian(req.getIsVegetarian());
            menuSettings.setIsNutFree(req.getIsNutFree());
            menuSettings.setIsDairyFree(req.getIsDairyFree());
            menuSettings.setIsGlutenFree(req.getIsGlutenFree());
            menuSettings.setCuisine(req.getCuisine());
            menuSettings.setExcluded(req.getExcluded());
            user.setMenuSettings(menuSettings);
            repository.save(user);
        }catch (Exception e){
            log.error(e.toString());
            throw new ProfileException("Failed to update menu settings");
        }
    }
    @Cacheable(value = "settings", key = "#authentication.name")
    public MenuSettingsRequestDTO getUserSettings(Authentication authentication) throws Exception{
        User user = utils.getUserByAuthentication(authentication);
        try {
            var menuSettings = user.getMenuSettings();
            return MenuSettingsRequestDTO
                    .builder()
                    .isVegetarian(menuSettings.getIsVegetarian())
                    .isDairyFree(menuSettings.getIsDairyFree())
                    .isGlutenFree(menuSettings.getIsGlutenFree())
                    .isNutFree(menuSettings.getIsNutFree())
                    .cuisine(menuSettings.getCuisine())
                    .excluded(menuSettings.getExcluded())
                    .build();
        }catch (Exception e){
            log.error("Failed to get user menu settings ", e);
            throw new ProfileException("Failed to get user menu settings ", e);
        }
    }
}
