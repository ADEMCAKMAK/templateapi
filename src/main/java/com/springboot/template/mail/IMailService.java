package com.springboot.template.mail;

import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface IMailService {

    void send(String to, String subject, String text) throws MessagingException;

    void send(List<String> to, String subject, String text) throws MessagingException;

    void send(String to, String subject, String text, List<MultipartFile> attachments) throws IOException, MessagingException;

    void send(List<String> to, String subject, String text, List<MultipartFile> attachments) throws IOException, MessagingException;
}
