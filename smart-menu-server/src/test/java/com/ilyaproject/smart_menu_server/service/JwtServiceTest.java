package com.ilyaproject.smart_menu_server.service;

import com.ilyaproject.smart_menu_server.config.details.CustomUserDetails;
import com.ilyaproject.smart_menu_server.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = JwtService.class)
@TestPropertySource(properties = "jwt.secret=bkerabugfi345braiulgberali43SEF4SEF2aogslih43raoGERAAGEahgoierah2h432hiogahoaehg")
public class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    private CustomUserDetails userDetails;

    private String token;

    @BeforeEach
    void setUp(){
        User user = User
                .builder()
                .id(1)
                .username("User")
                .email("email@email.com")
                .password("password123")
                .build();
        userDetails = new CustomUserDetails(user);
        token = jwtService.generateToken(userDetails);
    }

    @Test
    void generateTokenTest_returnString() throws Exception{
        String testToken = jwtService.generateToken(userDetails);
        assertNotNull(testToken);
    }

    @Test
    void extractIDTest_returnInteger(){
        assertNotNull(token);
        Integer id = jwtService.extractID(token);
        assertNotNull(id);
        assertEquals(1, id);
    }

    @Test
    void isTokenValidTest_correctUserDetails_returnBoolean(){
        assertNotNull(token);
        Boolean isValid = jwtService.isTokenValid(token, userDetails);
        assertTrue(isValid);
    }

    @Test
    void isTokenValidTest_wrongUserDetails_returnBoolean(){
        assertNotNull(token);
        User user = User
                .builder()
                .id(2)
                .username("User2")
                .email("email2@email.com")
                .password("password456")
                .build();
        CustomUserDetails anotherUserDetails = new CustomUserDetails(user);
        Boolean isValid = jwtService.isTokenValid(token, anotherUserDetails);
        assertFalse(isValid);
    }

}
