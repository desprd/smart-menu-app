package com.ilyaproject.smart_menu_server.service;

import com.ilyaproject.smart_menu_server.dto.credentials.ChangeCredentialsDTO;
import com.ilyaproject.smart_menu_server.model.User;
import com.ilyaproject.smart_menu_server.repository.UserRepository;
import com.ilyaproject.smart_menu_server.utils.UserUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.security.auth.login.CredentialException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CredentialsService {
    private final UserUtils utils;
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    public void changeCredentials(ChangeCredentialsDTO req, Authentication authentication) throws Exception{
        User user = utils.getUserByAuthentication(authentication);
        try {
            user.setEmail(req.getEmail());
            user.setPassword(encoder.encode(req.getPassword()));
            repository.save(user);
        }catch (Exception e){
            log.error("Failed to change users credentials ", e);
            throw new CredentialException("Failed to change users credentials ");
        }
    }
}
