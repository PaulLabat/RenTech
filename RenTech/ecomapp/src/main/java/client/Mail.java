package client;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Cirie on 03/12/2014.
 */
public class Mail {

    public static void main(String[] args)
    {
        /***** CHANGE THESE FOUR VARIABLE VALUES TO REFLECT YOUR ENVIRONMENT ******/
        String user = "no-reply";  // Newly created user on JAMES
        String password = "test"; // user password

        String fromAddress = "no-reply@rentech.com";
        String toAddress = "nexucis@gmail.com";


        // Create a mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.username", user);
        properties.put("mail.smtp.password", password);
        Session session = Session.getDefaultInstance(properties, null);

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));

            message.setSubject("Email from our JAMES Server");
            message.setText("Luke, I'm your father!!");
            Transport.send(message);

            System.out.println("Email sent successfully");
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
}
