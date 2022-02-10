package com.web.microservice.mailservice.service;

import com.web.microservice.mailservice.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendEmail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("officalsurenanthony@gmail.com");
        mailMessage.setTo("surenanthonyvithanage@gmail.com");
        mailMessage.setSubject("Mail Server - Microservice");
        mailMessage.setText("Hi user!!");
        javaMailSender.send(mailMessage);
    }


    @Override
    public void sendAdvancedEmail(Mail mail) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.addAttachment("profile.jpg", new ClassPathResource("profile.jpg"));

            Context context = new Context();

            context.setVariables(mail.getProps());
            String html = templateEngine.process("newsletter-template", context);
            helper.setTo(mail.getMailTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailWithAttachment() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        // multipart message
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo("surenanthonyvithanage@gmail.com");
        helper.setSubject("Mail Server - Microservice with attachment");
        helper.setText("<h1>Check attachment</h1>", true);
        helper.addAttachment("developer.jpg", new ClassPathResource("profile.jpg"));
        javaMailSender.send(mimeMessage);
    }


}
