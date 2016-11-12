package com.mirror2.messaging.threads;

import com.mirror2.util.MirrorConstants;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 02/09/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 02/09/15
 * Version : 1.0
 */

public class EmailThread extends Thread{
    private String from;
    private String to;
    private String sub;
    private String body;

    public EmailThread(String from, String to, String sub, String body) {
        this.from = from;
        this.to = to;
        this.sub = sub;
        this.body = body;
    }

    public void run() {
        final String username = "cs.dorpan@gmail.com";
        final String password = "Mak12345";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));   //to
            message.setSubject(sub);
            message.setText(body+ MirrorConstants.EMAIL_SUFFIX);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
