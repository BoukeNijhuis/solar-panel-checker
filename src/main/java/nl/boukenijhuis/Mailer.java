package nl.boukenijhuis;

import org.jetbrains.annotations.NotNull;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class Mailer {

    public static void sendEmail(Properties properties, String subject) throws IOException {

        String username = (String) properties.get("username");
        String password = (String) properties.get("password");
        String from = "sender@gmail.com";

        Session session = Session.getInstance(getMailProperties(), new javax.mail.Authenticator(){
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(username));
            message.setSubject(subject);
            message.setText("");

            Transport.send(message);
            System.out.println("mail send");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    @NotNull
    private static Properties getMailProperties() {
        Properties mailProperties = new Properties();

        mailProperties.put("mail.smtp.host", "smtp.gmail.com");
        mailProperties.put("mail.smtp.port", 587);
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", "true");
        mailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return mailProperties;
    }
}
