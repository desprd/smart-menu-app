package com.ilyaproject.smart_menu_server.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.ilyaproject.smart_menu_server.config.details.CustomUserDetails;
import com.ilyaproject.smart_menu_server.dto.MenuSettingsRequestDTO;
import com.ilyaproject.smart_menu_server.dto.ProfileInformationRequestDTO;
import com.ilyaproject.smart_menu_server.exception.ProfileException;
import com.ilyaproject.smart_menu_server.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuSettingsService {
    private final UserRepository repository;
    @Transactional
    public void updateMenuService(@RequestBody @Valid MenuSettingsRequestDTO req,
                                  Authentication authentication) throws Exception{
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        var userId = userDetails.getUser().getId();
        var user = repository.findById(userId)
                .orElseThrow(() -> new ProfileException("User not found"));
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
}
