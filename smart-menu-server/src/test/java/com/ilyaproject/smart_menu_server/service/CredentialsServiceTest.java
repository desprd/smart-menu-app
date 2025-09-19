package com.ilyaproject.smart_menu_server.service;

import com.ilyaproject.smart_menu_server.config.details.CustomUserDetails;
import com.ilyaproject.smart_menu_server.dto.credentials.ChangeCredentialsDTO;
import com.ilyaproject.smart_menu_server.email.sender.PasswordResetTokenEmailSender;
import com.ilyaproject.smart_menu_server.exception.ResetPasswordTokenException;
import com.ilyaproject.smart_menu_server.model.*;
import com.ilyaproject.smart_menu_server.repository.PasswordResetTokenRepository;
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
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class CredentialsServiceTest {
    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private Cleaner cleaner;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @MockitoBean
    private PasswordResetTokenEmailSender sender;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

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
    void sendLinkToChangePasswordTest_void() throws Exception{
        doNothing().when(sender).sendPasswordResetTokenEmail(anyString(), anyString());
        credentialsService.sendLinkToChangePassword(user.getEmail());
        System.out.println(tokenRepository.findByUserId(user.getId()).getToken());
        assertNotNull(tokenRepository.findByUserId(user.getId()));
    }

    @Test
    void changeCredentialsTest_void() throws Exception{
        ChangeCredentialsDTO credentials = new ChangeCredentialsDTO("newemail@email.com", "newpassword");
        credentialsService.changeCredentials(credentials, auth);
        assertEquals("newemail@email.com", repository.findById(user.getId()).get().getEmail());
    }

    @Test
    void validateTokenTest_void() throws Exception{
        PasswordResetToken token = PasswordResetToken
                                    .builder()
                                    .token("test")
                                    .user(user)
                                    .expiryDate(new Date(System.currentTimeMillis() + 60*60*1000))
                                    .build();
        tokenRepository.save(token);
        credentialsService.validateToken("test");
    }

    @Test
    void validateExpiredTokenTest_throwsResetPasswordTokenException() throws Exception{
        PasswordResetToken token = PasswordResetToken
                .builder()
                .token("test")
                .user(user)
                .expiryDate(new Date(System.currentTimeMillis()))
                .build();
        tokenRepository.save(token);
        assertThrows(ResetPasswordTokenException.class, () -> credentialsService.validateToken("test"));
    }

    @Test
    void resetPasswordTest_void() throws Exception{
        PasswordResetToken token = PasswordResetToken
                .builder()
                .token("test")
                .user(user)
                .expiryDate(new Date(System.currentTimeMillis()))
                .build();
        tokenRepository.save(token);
        credentialsService.resetPassword("test", "newpassword");
        assertTrue(encoder.matches("newpassword", repository.findById(user.getId()).get().getPassword()));
    }

}
