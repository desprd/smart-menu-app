package com.ilyaproject.smart_menu_server.service;

import com.ilyaproject.smart_menu_server.config.details.CustomUserDetails;
import com.ilyaproject.smart_menu_server.dto.auth.AuthResponseDTO;
import com.ilyaproject.smart_menu_server.dto.auth.LoginRequestDTO;
import com.ilyaproject.smart_menu_server.dto.auth.SignUpRequestDTO;
import com.ilyaproject.smart_menu_server.dto.general.VerifyResponseDTO;
import com.ilyaproject.smart_menu_server.exception.AuthException;
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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AuthenticationServiceTest {

    @Autowired
    private UserRepository repository;

    @MockitoBean
    private JwtService jwtService;

    @Autowired
    private Cleaner cleaner;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationService authenticationService;

    private User user;

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
    }

    @Test
    void signUpTest_returnAuthResponseDTO() throws Exception{
        SignUpRequestDTO sign = new SignUpRequestDTO("User2","email2@email.com", "password2");
        when(jwtService.generateToken(any(CustomUserDetails.class))).thenReturn("token123");
        AuthResponseDTO auth = authenticationService.signUp(sign);
        assertNotNull(auth);
        assertEquals("User2", auth.getUsername());
        assertEquals(2, repository.findAll().size());
    }

    @Test
    void signUpSameEmailTest_throwsAuthException(){
        SignUpRequestDTO sign = new SignUpRequestDTO("User2","email@email.com", "password2");
        assertThrows(AuthException.class, () -> authenticationService.signUp(sign));
    }

    @Test
    void loginTest_returnAuthResponseDTO() throws Exception{
        LoginRequestDTO loginRequest = new LoginRequestDTO("email@email.com", "password");
        when(jwtService.generateToken(any(CustomUserDetails.class))).thenReturn("token123");
        AuthResponseDTO response = authenticationService.login(loginRequest);
        assertNotNull(response);
        assertEquals("User", response.getUsername());
    }

    @Test
    void loginInvalidCredentialsTest_throwsBadCredentialsException(){
        LoginRequestDTO loginRequest = new LoginRequestDTO("anotheremail@email.com", "password");
        assertThrows(AuthException.class, () -> authenticationService.login(loginRequest));
    }

    @Test
    void verifyTest_returnVerifyResponseDTO(){
        CustomUserDetails details = new CustomUserDetails(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                details, null, details.getAuthorities()
        );
        VerifyResponseDTO response = authenticationService.verify(auth);
        assertNotNull(response);
    }

}
