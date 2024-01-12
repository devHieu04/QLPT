package Mailer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    public static void sendEmail(String recipient, String password) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gianght.23da@vku.udn.vn","Cap@13035");
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("gianght.23da@vku.udn.vn"));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
        message.setSubject("Test Mail");
        message.setText("This is a test mail");

        Transport.send(message);
        System.out.println("Mail sent successfully...");
    }


}

