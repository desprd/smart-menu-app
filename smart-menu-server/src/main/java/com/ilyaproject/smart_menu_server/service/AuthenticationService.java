package com.ilyaproject.smart_menu_server.service;

import com.ilyaproject.smart_menu_server.config.details.CustomUserDetails;
import com.ilyaproject.smart_menu_server.dto.auth.LoginRequestDTO;
import com.ilyaproject.smart_menu_server.dto.auth.SignUpRequestDTO;
import com.ilyaproject.smart_menu_server.dto.auth.AuthResponseDTO;
import com.ilyaproject.smart_menu_server.dto.general.VerifyResponseDTO;
import com.ilyaproject.smart_menu_server.exception.AuthException;
import com.ilyaproject.smart_menu_server.model.MenuSettings;
import com.ilyaproject.smart_menu_server.model.ProfileInformation;
import com.ilyaproject.smart_menu_server.model.Role;
import com.ilyaproject.smart_menu_server.model.User;
import com.ilyaproject.smart_menu_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Transactional
    public AuthResponseDTO signUp(SignUpRequestDTO req) throws Exception{
        String username = req.getUsername();
        String email = req.getEmail();
        if (repository.existsByEmail(email)){
            throw new AuthException("User with this email already exists");
        }
        try {
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
            var user = User
                    .builder()
                    .username(username)
                    .email(email)
                    .role(Role.USER)
                    .password(encoder.encode(req.getPassword()))
                    .profileInformation(profileInfo)
                    .menuSettings(menuSettings)
                    .build();
            repository.save(user);
            var token = jwtService.generateToken(new CustomUserDetails(user));
            return AuthResponseDTO
                    .builder()
                    .email(email)
                    .username(username)
                    .token(token)
                    .build();
        }catch (Exception e){
            log.error(e.toString());
            throw new AuthException("Failed to create profile");
        }
    }
    public AuthResponseDTO login(LoginRequestDTO req) throws Exception{
        Authentication authentication = null;
        try {
            authentication =authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                req.getEmail(),
                req.getPassword()
              )
            );
        }catch (BadCredentialsException e){
            log.error(e.toString());
            throw new AuthException("Username or password are incorrect", e);
        }
        if (authentication == null){
            throw new AuthException("Something went wrong during validation");
        }
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        var user = userDetails.getUser();
        var token = jwtService.generateToken(userDetails);
        return AuthResponseDTO
                .builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .token(token)
                .build();
    }
    public VerifyResponseDTO verify(Authentication authentication){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        var user = userDetails.getUser();
        return VerifyResponseDTO
                .builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }

}
