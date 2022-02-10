package com.web.microservice.mailservice.service;

import com.web.microservice.mailservice.model.Mail;

import javax.mail.MessagingException;

public interface MailService {
    void sendEmail();
    void sendAdvancedEmail(Mail mail);
    void sendEmailWithAttachment() throws MessagingException;
}
