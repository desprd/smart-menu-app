package com.ilyaproject.smart_menu_server.email.sender;

import com.ilyaproject.smart_menu_server.email.generator.PasswordResetMessageGenerator;
import com.ilyaproject.smart_menu_server.exception.EmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenEmailSender{
    private final PasswordResetMessageGenerator messageGenerator;
    private final MailSender sender;
    public void sendPasswordResetTokenEmail(String url, String email) throws Exception{
        try {
            SimpleMailMessage mailMessage = messageGenerator.constructResetPasswordEmail(url, email);
            sender.send(mailMessage);
        }catch (Exception e){
            throw new EmailException("Failed to send email with password reset token ", e);
        }
    }
}
