package com.ilyaproject.smart_menu_server.email.generator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetMessageGenerator {
    @Value("${spring.mail.username}")
    private String emailName;
    public SimpleMailMessage constructResetPasswordEmail(String url, String email){
        String message = "To change password for your Smart Menu account, please click link below" + " \r\n" + url;
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Reset password");
        mailMessage.setText(message);
        mailMessage.setTo(email);
        mailMessage.setFrom(emailName);
        return mailMessage;
    }
}
