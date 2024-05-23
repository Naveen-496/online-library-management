package dev.reddy.olm.service.impl;

import dev.reddy.olm.exception.ApiException;
import dev.reddy.olm.service.EmailService;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static dev.reddy.olm.util.EmailUtil.getEmailMessage;
import static dev.reddy.olm.util.EmailUtil.getMessagePasswordReset;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
    private static final String PASSWORD_RESET_REQUEST = "Password Reset Request";
    private final JavaMailSender mailSender;
    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;


    @Override
    @Async
    public void sendNewAccountEmail(String name, String toEmail, String key) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, toEmail);
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setContent(getEmailMessage(name, host, key), "text/html; charset=utf-8");
            mailSender.send(message);

        }catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException("Unable to send email");
        }
    }

    @Override
    @Async
    public void sendPasswordResetEmail(String name, String toEmail, String key) {
        try {
            var simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject(PASSWORD_RESET_REQUEST);
            simpleMailMessage.setFrom(fromEmail);
            simpleMailMessage.setTo(toEmail);
            simpleMailMessage.setText(getMessagePasswordReset(name, host, toEmail));
            mailSender.send(simpleMailMessage);
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException("Unable to send email");
        }
    }
}
