package com.ilyaproject.smart_menu_server.service;

import com.ilyaproject.smart_menu_server.config.details.CustomUserDetails;
import com.ilyaproject.smart_menu_server.dto.profile.MenuSettingsRequestDTO;
import com.ilyaproject.smart_menu_server.model.MenuSettings;
import com.ilyaproject.smart_menu_server.model.ProfileInformation;
import com.ilyaproject.smart_menu_server.model.Role;
import com.ilyaproject.smart_menu_server.model.User;
import com.ilyaproject.smart_menu_server.repository.UserRepository;
import com.ilyaproject.smart_menu_server.utils.Cleaner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class MenuSettingsServiceTest {
    @Autowired
    private UserRepository repository;

    @Autowired
    private Cleaner cleaner;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private MenuSettingsService menuSettingsService;

    private User user;

    private Authentication auth;

    @BeforeEach
    void setUp(){
        var profileInfo = ProfileInformation
                .builder()
                .weight(60.0)
                .height(180.0)
                .age(21)
                .sex("Male")
                .activity("Moderate")
                .goals("Maintenance")
                .build();
        var menuSettings = MenuSettings
                .builder()
                .isDairyFree(false)
                .isGlutenFree(false)
                .isNutFree(false)
                .isVegetarian(false)
                .cuisine("Any")
                .excluded("")
                .build();
        user = User
                .builder()
                .username("User")
                .email(cleaner.cleanEmail("email@email.com"))
                .role(Role.USER)
                .password(encoder.encode("password"))
                .profileInformation(profileInfo)
                .menuSettings(menuSettings)
                .build();
        repository.save(user);
        CustomUserDetails details = new CustomUserDetails(user);
        auth = new UsernamePasswordAuthenticationToken(
                details, null, details.getAuthorities()
        );
    }

    @Test
    void updateMenuServiceTest_void() throws Exception{
        MenuSettingsRequestDTO req = MenuSettingsRequestDTO
                .builder()
                .isVegetarian(true)
                .isGlutenFree(true)
                .isNutFree(true)
                .isDairyFree(true)
                .cuisine("American")
                .excluded("Shrimps")
                .build();
        menuSettingsService.updateMenuService(req, auth);
        User updatedUser = repository.findById(user.getId()).orElseThrow();
        MenuSettings menuSettings = updatedUser.getMenuSettings();
        assertTrue(menuSettings.getIsDairyFree());
        assertTrue(menuSettings.getIsNutFree());
        assertTrue(menuSettings.getIsVegetarian());
        assertTrue(menuSettings.getIsGlutenFree());
        assertEquals("American", menuSettings.getCuisine());
        assertEquals("Shrimps", menuSettings.getExcluded());
    }

    @Test
    void getUserSettingsTest_returnMenuSettingsRequestDTO() throws Exception{
        MenuSettingsRequestDTO menuSettings = menuSettingsService.getUserSettings(auth);
        assertFalse(menuSettings.getIsDairyFree());
        assertFalse(menuSettings.getIsNutFree());
        assertFalse(menuSettings.getIsVegetarian());
        assertFalse(menuSettings.getIsGlutenFree());
        assertEquals("Any", menuSettings.getCuisine());
        assertEquals("", menuSettings.getExcluded());
    }
}
