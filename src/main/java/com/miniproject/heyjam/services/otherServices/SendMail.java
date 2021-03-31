package com.miniproject.heyjam.services.otherServices;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static javax.mail.Transport.send;

public class SendMail{
    public static boolean prepareMail(String recipient,String key){
        System.out.println("Preparing to Send email");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String MyEmail="####";
        String MyPassword="####";

        Session session= Session.getInstance(properties,new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(MyEmail,MyPassword);
            }
        });

        Message message= prepareMessage(session, MyEmail,recipient,key);
        try {
            send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Message prepareMessage(Session session, String myEmail, String recipient, String key) {
        Message message=new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
            message.setSubject("Activation Key: Hey!JAM");
            message.setText("Your Activation Key: "+key);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        prepareMail("####","12345");
    }
}