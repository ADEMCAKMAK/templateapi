package com.springboot.template.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MailService implements IMailService {

    private JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(String to, String subject, String text) throws MessagingException {

        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        mailSender.send(msg);
    }

    @Override
    public void send(List<String> to, String subject, String text) throws MessagingException {
        for(String item : to)
            this.send(item, subject, text);
    }

    @Override
    public void send(String to, String subject, String text, MultipartFile attachment)
            throws MessagingException, IOException {

        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        DataSource dataSource = new ByteArrayDataSource(attachment.getBytes(), attachment.getContentType());
        helper.addAttachment(attachment.getName(), dataSource);
        mailSender.send(msg);
    }

    @Override
    public void send(List<String> to, String subject, String text, MultipartFile attachment)
            throws IOException, MessagingException {
        for(String item : to)
            this.send(item, subject, text, attachment);
    }
}
