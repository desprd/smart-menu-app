package com.ilyaproject.smart_menu_server.email.generator;

import com.ilyaproject.smart_menu_server.dto.menu.json.RecipesDTO;
import com.ilyaproject.smart_menu_server.email.generator.html.RecipeHtmlGenerator;
import com.ilyaproject.smart_menu_server.email.generator.pdf.PdfGenerator;
import com.ilyaproject.smart_menu_server.exception.EmailException;
import com.ilyaproject.smart_menu_server.exception.GenerationException;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipesInPdfMessageGenerator {
    @Value("${spring.mail.username}")
    private String emailName;
    @Value("${spring.mail.password}")
    private String password;
    private final PdfGenerator pdfGenerator;
    private final RecipeHtmlGenerator htmlGenerator;

    public MimeMessage constructRecipesMessage(String targetEmail, RecipesDTO recipes) throws Exception{
        try {
            List<String> htmlList = htmlGenerator.generateListOfRecipesHtml(recipes);
            byte[] pdfs = pdfGenerator.generateMergedPdfs(htmlList);
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailName, password);
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailName));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(targetEmail));
            message.setSubject("Smart menu for this week");
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Attached to this email, you will find a PDF file with your menu for this week");
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new ByteArrayDataSource(pdfs, "application/octet-stream");
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName("menu.pdf");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);
            return message;
        }catch (Exception e){
            throw new GenerationException("Failed to generate email with menu.pdf ", e);
        }
    }
}
