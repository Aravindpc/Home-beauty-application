package com.ourapp.salonapp.service;
 
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ourapp.salonapp.Exception.BadMessageException;
import com.ourapp.salonapp.dto.Maildto;
 
@Service
public class MailService {
 
    @Autowired
    JavaMailSender mailSender;
 
    public void sendEmail(Maildto mail) throws UnknownHostException, BadMessageException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
 
        try {
 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
 
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "tresbeaux.com"));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());
 
            mailSender.send(mimeMessageHelper.getMimeMessage());
 
        } catch (MessagingException e) {
           throw new BadMessageException("Internet Not available");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    } 
}