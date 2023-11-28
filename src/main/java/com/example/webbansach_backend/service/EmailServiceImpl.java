package com.example.webbansach_backend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService{
    private JavaMailSender emailSender;
    @Override
    public void sendMessage(String from, String to, String subject, String textContent) {
        // MimeMailMessage used with file, ...
//        SimpleMailMessage used without file

        MimeMessage message = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(textContent, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        // we can also set "replyTo"

        emailSender.send(message);
    }


}
