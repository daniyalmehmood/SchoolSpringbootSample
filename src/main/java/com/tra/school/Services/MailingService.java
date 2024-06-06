package com.tra.school.Services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;

public class MailingService {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleMail(String toEmail, String fromEmail, String emailBody,
                                 String subject) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(fromEmail);
            mailMessage.setTo(toEmail);
            mailMessage.setText(emailBody);
            mailMessage.setSubject(subject);
            mailSender.send(mailMessage);
            return "Success";
        } catch (Exception e) {
            return "Error";
        }
    }

    public String sendMailWithAttachment() {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo("muhammad.daniyal@codeline.rihal.om");
            mimeMessageHelper.setText("Hello Email Body");
            mimeMessageHelper.setSubject("This is the subject");
            FileSystemResource file = new FileSystemResource(new File("Path to local file"));
            mimeMessageHelper.addAttachment(file.getFilename(), file);
            mailSender.send(mimeMessage);
            return "Success";
        }
        catch (MessagingException e) {
            return "Error";
        }
    }
}
