package classes.core;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static classes.Verificator.generateVal;

public class Emailer {

    static Properties prop = new Properties();
    static DateTimeFormatter dateF = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");


    static String authUser = "shu_ru_pin@mail.ru";
    static String authPass = "AUTYo/ofri12";
    static String emailFrom = "shu_ru_pin@mail.ru";
    static String authServ = "smtp.mail.ru";
    static String port = "465";


    public static void sendEmail(String emailTo, String subj, String body) {


        prop.put("mail.smtp.host", authServ);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.ssl.trust", authServ);
        prop.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        prop.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        prop.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication


        System.out.println("prop=" + prop);

        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(authUser, authPass);
            }
        });

        try {
            // MimeBodyPart attachmentBodyPart = new MimeBodyPart();


            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(emailFrom));

            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject(subj);
            //    System.out.println("      after session message.setSubject    ");
            String msg = body;
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
            //    System.out.println("      after imeBodyPart.setContent(msg, \"text/html\");  \n" + "----msg: " + msg);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            ///   System.out.println("      after multipart.addBodyPart(mimeBodyPart);   ");
            message.setContent(multipart);

            ///  System.out.println("      message all:" + message.getAllRecipients());
            // multipart.addBodyPart(attachmentBodyPart);

            Transport.send(message);
            System.out.println("      after send   ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        generateVal("shurupin@gmail.com");
    }
}
