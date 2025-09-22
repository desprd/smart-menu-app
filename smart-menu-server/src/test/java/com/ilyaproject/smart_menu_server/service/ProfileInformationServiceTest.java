package com.ilyaproject.smart_menu_server.service;

import com.ilyaproject.smart_menu_server.config.details.CustomUserDetails;
import com.ilyaproject.smart_menu_server.dto.profile.ProfileInformationRequestDTO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ProfileInformationServiceTest {
    @Autowired
    private UserRepository repository;

    @Autowired
    private Cleaner cleaner;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ProfileInformationService profileInformationService;

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
    void updateProfileInformationTest_void() throws Exception{
        ProfileInformationRequestDTO profileInformation = ProfileInformationRequestDTO
                .builder()
                .weight(80.0)
                .height(190.0)
                .age(34)
                .sex("Male")
                .activity("Moderate")
                .goals("Bulk")
                .build();
        profileInformationService.updateProfileInformation(profileInformation, auth);
        User updatedUser = repository.findById(user.getId()).orElseThrow();
        ProfileInformation information = updatedUser.getProfileInformation();
        assertEquals(80.0, information.getWeight());
        assertEquals(190.0, information.getHeight());
        assertEquals(34, information.getAge());
        assertEquals("Male", information.getSex());
        assertEquals("Moderate", information.getActivity());
        assertEquals("Bulk", information.getGoals());
    }

    @Test
    void getProfileInformationTest_returnProfileInformationRequestDTO() throws Exception{
        ProfileInformationRequestDTO information = profileInformationService.getProfileInformation(auth);
        assertEquals(60.0, information.getWeight());
        assertEquals(180.0, information.getHeight());
        assertEquals(21, information.getAge());
        assertEquals("Male", information.getSex());
        assertEquals("Moderate", information.getActivity());
        assertEquals("Maintenance", information.getGoals());
    }

}
