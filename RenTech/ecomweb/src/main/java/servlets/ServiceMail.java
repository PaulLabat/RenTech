package servlets;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ejb.entity.Commande;
import ejb.entity.Utilisateur;

public class ServiceMail {
	
	static String usermail = "no-reply";
	static String passwordmail = "test";
	static String fromAddress = "no-reply@rentech.com";
	
	public static void sendMailNewUser(Utilisateur User){
      
        
        String toAddress = User.getMail();


        // Create a mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.username", usermail);
        properties.put("mail.smtp.password", passwordmail);
        Session session = Session.getDefaultInstance(properties, null);

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));

            message.setSubject("Compte crée sur RenTech!!");
            message.setText("Bonjour "+User.getPrenom()+" "+User.getNom()+"! </BR></BR>"
            		+ "Vous venez de vous créez un compte sur RenTech.</BR></BR>"
            		+ "Veuillez activer votre compte au lien suivant : <BR>"
            		+ "http://paul-pc:8080/ecom/ValidationServlet?email="+User.getMail()+" </BR></BR>"
            		+ "Merci de votre confiance.<BR><BR>L'équipe RenTech");
            Transport.send(message);

            System.out.println("Email sent successfully");
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
	
public static void sendMailCompteActive(String email){
      
        
        String toAddress = email;


        // Create a mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.username", usermail);
        properties.put("mail.smtp.password", passwordmail);
        Session session = Session.getDefaultInstance(properties, null);

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));

            message.setSubject("Compte crée sur RenTech!!");
            message.setText("Bonjour! </BR></BR>"
            		+ "Vous venez d'activer compte sur RenTech.</BR></BR>"
            		+ "Merci de votre confiance.<BR><BR>L'équipe RenTech");
            Transport.send(message);

            System.out.println("Email sent successfully");
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
	
	public static void sendMailDeleteUser(Utilisateur User){


        String fromAddress = "no-reply@rentech.com";
        String toAddress = User.getMail();


        // Create a mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.username", usermail);
        properties.put("mail.smtp.password", passwordmail);
        Session session = Session.getDefaultInstance(properties, null);

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));

            message.setSubject("Compte supprimé sur RenTech");
            message.setText("Bonjour "+User.getPrenom()+" "+User.getNom()+"! <BR><BR>Vous venez de supprimer compte sur RenTech.<BR><BR>L'équipe RenTech");
            Transport.send(message);

            System.out.println("Email sent successfully");
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
	
	public static void sendMailModifyUser(Utilisateur User){

        String toAddress = User.getMail();


        // Create a mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.username", usermail);
        properties.put("mail.smtp.password", passwordmail);
        Session session = Session.getDefaultInstance(properties, null);

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));

            message.setSubject("Informations modifiées sur RenTech");
            message.setText("Bonjour "+User.getPrenom()+" "+User.getNom()+"! <BR><BR>"
            		+ "Vous venez de modifiez vos informations de compte sur RenTech.<BR><BR>"
            		+ "Vos nouvelles informations sont : <BR><BR>"
            		+ "Email : "+User.getMail()+"<BR>"
            		+ "Nom : "+User.getNom()+"<BR>"
            		+ "Prénom : "+User.getPrenom()+"<BR>"
            		+ "Adresse : "+User.getAdresseFactu()+"<BR>"
            		+ "<BR><BR>L'équipe RenTech");
            Transport.send(message);

            System.out.println("Email sent successfully");
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
	
	public static void sendMailCommande(Utilisateur User,Commande commande){

        String toAddress = User.getMail();


        // Create a mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.username", usermail);
        properties.put("mail.smtp.password", passwordmail);
        Session session = Session.getDefaultInstance(properties, null);

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));

            message.setSubject("Informations sur votre commande");
            message.setText("Bonjour "+User.getPrenom()+" "+User.getNom()+"! <BR><BR>"
            		+ "Vous venez de passer une commande chez RenTech.<BR><BR>"
            		+ "Informations sur votre commande : <BR><BR>"
            		+ "ID de la commande : "+commande.getId()+"<BR>"
            		+ "Adresse de facturation : "+commande.getAdresseFactu()+"<BR><BR>"
            		+ "Merci pour votre commande !"
            		+ "<BR><BR>L'équipe RenTech");
            Transport.send(message);

            System.out.println("Email sent successfully");
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

}
