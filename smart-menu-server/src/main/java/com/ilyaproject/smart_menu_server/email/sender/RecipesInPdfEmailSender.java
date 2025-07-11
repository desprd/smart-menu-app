package com.ilyaproject.smart_menu_server.email.sender;

import com.ilyaproject.smart_menu_server.dto.menu.json.RecipesDTO;
import com.ilyaproject.smart_menu_server.email.generator.RecipesInPdfMessageGenerator;
import com.ilyaproject.smart_menu_server.exception.EmailException;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipesInPdfEmailSender {
    private final RecipesInPdfMessageGenerator generator;
    public void sendRecipesInPdfEmail(String targetEmail, RecipesDTO recipes) throws Exception{
        try {
            MimeMessage message = generator.constructRecipesMessage(targetEmail, recipes);
            Transport.send(message);
        }catch (Exception e){
            throw new EmailException("Failed to send email with menu.pdf in attachments");
        }
    }
}
