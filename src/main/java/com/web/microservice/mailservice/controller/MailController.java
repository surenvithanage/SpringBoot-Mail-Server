package com.web.microservice.mailservice.controller;

import com.web.microservice.mailservice.model.Mail;
import com.web.microservice.mailservice.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("mail")
@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("send")
    public void initiateMail() {
        this.mailService.sendEmail();
    }

    @GetMapping("send/attachment")
    public void initiateMailAttachment() throws MessagingException {
        this.mailService.sendEmailWithAttachment();
    }

    @GetMapping("send/template")
    public void initiateMailTemplate() {
        Mail mail = new Mail();
        mail.setFrom("freelancingbysuren@gmail.com");
        mail.setMailTo("surenanthonyvithanage@gmail.com");
        mail.setSubject("Email with Spring boot and thymeleaf template!");
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", "Developer!");
        model.put("location", "United States");
        model.put("sign", "Java Developer");
        mail.setProps(model);
        this.mailService.sendAdvancedEmail(mail);
    }
}
