package com.ilyaproject.smart_menu_server.service;

import com.ilyaproject.smart_menu_server.dto.credentials.ChangeCredentialsDTO;
import com.ilyaproject.smart_menu_server.email.sender.PasswordResetTokenEmailSender;
import com.ilyaproject.smart_menu_server.exception.AuthException;
import com.ilyaproject.smart_menu_server.exception.ProfileException;
import com.ilyaproject.smart_menu_server.exception.ResetPasswordTokenException;
import com.ilyaproject.smart_menu_server.model.PasswordResetToken;
import com.ilyaproject.smart_menu_server.model.User;
import com.ilyaproject.smart_menu_server.repository.PasswordResetTokenRepository;
import com.ilyaproject.smart_menu_server.repository.UserRepository;
import com.ilyaproject.smart_menu_server.utils.Cleaner;
import com.ilyaproject.smart_menu_server.utils.UserUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.security.auth.login.CredentialException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CredentialsService {
    private final UserUtils utils;
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordResetTokenEmailSender sender;
    private final Cleaner cleaner;
    @Value("${url.path}")
    private String path;
    public void changeCredentials(ChangeCredentialsDTO req, Authentication authentication) throws Exception{
        User user = utils.getUserByAuthentication(authentication);
        try {
            user.setEmail(cleaner.cleanEmail(req.getEmail()));
            user.setPassword(encoder.encode(req.getPassword()));
            repository.save(user);
        }catch (Exception e){
            log.error("Failed to change users credentials ", e);
            throw new CredentialException("Failed to change users credentials ");
        }
    }

    public void sendLinkToChangePassword(String email) throws Exception{
        String cleanEmail = cleaner.cleanEmail(email);
        User user = repository.findByEmail(cleanEmail).orElseThrow(() -> new AuthException("Failed to find user by email"));
        try {
            deleteTokenIfExists(user.getId());
            PasswordResetToken resetToken = createPasswordResetToken(user);
            tokenRepository.save(resetToken);
            String url = urlGenerator(resetToken.getToken());
            sender.sendPasswordResetTokenEmail(url, cleanEmail);
        }catch (Exception e){
            log.error("Failed to send link with reset password token " + e);
            throw new CredentialException("Failed to send link with reset password token " + e);
        }

    }

    public void validateToken(String token) throws Exception{
        PasswordResetToken resetToken = tokenRepository.findByToken(token).orElseThrow(() -> new ResetPasswordTokenException("Failed to fined token "));
        if (resetToken == null){
            throw new ResetPasswordTokenException("Token object is empty");
        }
        if (isTokenExpired(resetToken)){
            throw new ResetPasswordTokenException("Token is expired");
        }
    }

    public void resetPassword(String token, String password) throws Exception{
        PasswordResetToken resetToken = tokenRepository.findByToken(token).orElseThrow(() -> new ResetPasswordTokenException("Failed to fined token "));
        if (resetToken == null){
            throw new ResetPasswordTokenException("Token object is empty");
        }
        User user = resetToken.getUser();
        if (user == null){
            throw new ResetPasswordTokenException("Failed to fetch user from token");
        }
        try {
            user.setPassword(encoder.encode(password));
            repository.save(user);
        }catch (Exception e){
            throw new CredentialException("Failed to save user with new password " + e);
        }
    }

    private Boolean isTokenExpired(PasswordResetToken resetToken){
        Calendar cal = Calendar.getInstance();
        return resetToken.getExpiryDate().before(cal.getTime());
    }
    private PasswordResetToken createPasswordResetToken(User user){
        String token = UUID.randomUUID().toString();
        return PasswordResetToken
                .builder()
                .token(token)
                .user(user)
                .expiryDate(new Date(System.currentTimeMillis() + 60*60*1000))
                .build();
    }
    private String urlGenerator(String token){
        return path + "resetpassword?token="+token;
    }
    private void deleteTokenIfExists(Integer id) throws Exception{
        try {
            PasswordResetToken resetToken = tokenRepository.findByUserId(id);
            if (resetToken != null){
                tokenRepository.delete(resetToken);
            }
        }catch (Exception e){
            throw new ResetPasswordTokenException("Failed to delete existing token ", e);
        }
    }
}
