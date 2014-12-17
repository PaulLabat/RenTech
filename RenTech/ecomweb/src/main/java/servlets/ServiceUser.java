package servlets;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.List;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.Session;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ejb.bean.UtilisateurFacadeRemote;
import ejb.entity.Utilisateur;

public class ServiceUser {
	
	static void onCreateUser(UtilisateurFacadeRemote ufi,Session session, JsonObject jsonObject)
    {
		String nom = jsonObject.get("name").getAsString();
		
        String email = jsonObject.get("email").getAsString();
        String password = jsonObject.get("password").getAsString();
        
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setMail(email);
        utilisateur.setMdp(password);
        
        
    	StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        generator.writeStartObject();
        generator.write("fonct", "createUser");
        
       if(!ufi.contains(utilisateur)) 
        {
    	   
    	   utilisateur = ufi.create(utilisateur);
	       if (utilisateur!=null)
	       {
	    	   generator.write("status", "OK"); 
	    	   ServiceMail.sendMailNewUser(utilisateur);
	       }
	       else generator.write("status", "FAIL");
        }
       else generator.write("status", "FAILEXIST");
        generator.write("nom", utilisateur.getNom());
        generator.write("email", utilisateur.getMail());
        generator.write("password", utilisateur.getMdp());
        generator.writeEnd();
        generator.close();
        
        try {
            session.getBasicRemote().sendText(writer.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
	static void onConnectUser(UtilisateurFacadeRemote ufi,Session session, JsonObject jsonObject) throws SQLException
    {   	
    	System.out.println("Debut onConnectUser");
    		
        String email = jsonObject.get("email").getAsString();
        String password = jsonObject.get("password").getAsString();
        
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMail(email);
        utilisateur.setMdp(password);
        StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        generator.writeStartObject();
        generator.write("fonct", "connectUser");
        //Test si l'utilisateur existe dans la base de données
        if (ufi.contains(utilisateur)&&ufi.isValidated(utilisateur.getMail()))
        {
        	generator.write("status", "OK");  
        }
       else 
        {
        	generator.write("status", "FAIL");
        }
        
        generator.write("email", email);    
        generator.writeEnd();
        generator.close();
      
        try {
            session.getBasicRemote().sendText(writer.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	   static void onDeleteAccount(UtilisateurFacadeRemote ufi, Session session, JsonObject jsonObject) {
		   String email = jsonObject.get("email").getAsString();
	    	
	    	System.out.println("On supprime le compte ayant l'email : "+email);
	    	Utilisateur utilisateur = new Utilisateur();
	        utilisateur.setMail(email);
	        
	    	//Suppression du compte sur la bdd
	    	ufi.remove(utilisateur);
	    	ServiceMail.sendMailDeleteUser(utilisateur);
		}
	   
	   static void onChangeInfos(UtilisateurFacadeRemote ufi,Session session, JsonObject jsonObject) {
			String oldEmail = jsonObject.get("oldEmail").getAsString();
			String newEmail = jsonObject.get("newEmail").getAsString();
			String newName = jsonObject.get("newName").getAsString();
			String newFirstName = jsonObject.get("newFirstName").getAsString();
			StringWriter writer = new StringWriter();
	        JsonGenerator generator = Json.createGenerator(writer);
			
			
			//On push les infos sur l'utilisateur ayant pour email oldEmail et on remplace les infos par newEmail, newName
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setMail(oldEmail);
			if (ufi.contains(utilisateur))
			{
			utilisateur.setMail(newEmail);
			utilisateur.setNom(newName);
			utilisateur.setPrenom(newFirstName);
			generator.writeStartObject().write("status", "OK")
	        	.write("newEmail", newEmail)
	        	.write("newName", newName)
	        	.write("newFirstName", newFirstName)
				.writeEnd();
	       
			ufi.edit(utilisateur);
			ServiceMail.sendMailModifyUser(utilisateur);
			
			}
			else 
			{
				generator.writeStartObject().write("status", "FAIL")
				.writeEnd();
				
			}
			//Renvoi des nouvelles infos au site 
			
	        
	        generator.close();
	      	
	        try {
	            session.getBasicRemote().sendText(writer.toString());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
			
			
		}
	   
	   static void onChangePassword(UtilisateurFacadeRemote ufi,Session session, JsonObject jsonObject) {
			String Email = jsonObject.get("Email").getAsString();
			String oldPassword = jsonObject.get("oldPassword").getAsString();
			String newPassword = jsonObject.get("newPassword").getAsString();
			StringWriter writer = new StringWriter();
	        JsonGenerator generator = Json.createGenerator(writer);
			
			
			//On push les infos sur l'utilisateur ayant pour email oldEmail et on remplace les infos par newEmail, newName
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setMail(Email);
			utilisateur.setMdp(oldPassword);
			if(ufi.contains(utilisateur))
			{
				utilisateur.setMdp(newPassword);
				ufi.edit(utilisateur);
				generator.writeStartObject().write("status", "OK")
						.write("newPassword", newPassword);
			}
			else 
			{
				generator.writeStartObject().write("status", "FAIL");	
				
			}
			
			
	        generator.writeStartObject().writeEnd();
	        generator.close();
	      	
	        try {
	            session.getBasicRemote().sendText(writer.toString());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
			
			
		}
	   
	   static void onGetInfoUser(UtilisateurFacadeRemote ufi,Session session, JsonObject jsonObject) {
			
		   
		   String Email = jsonObject.get("email").getAsString();
		   Utilisateur user = ufi.getUser(Email);
		   if(user.getPrenom()==null)
		   {
			   user.setPrenom("");
		   }
		   if(user.getAdresseFactu()==null)
		   {
			   user.setAdresseFactu("");
		   }
		  
		   System.out.println(user.toString());
			StringWriter writer = new StringWriter();
	        JsonGenerator generator = Json.createGenerator(writer);
	        generator.writeStartObject()
	        	.write("fonct", "getInfosUser")
	        	.write("nom", user.getNom())
	        	.write("prenom", user.getPrenom())
	        	.write("mail",user.getMail())
	        	.write("password",user.getMdp())
	        	.write("adresseFactu",user.getAdresseFactu())
	        	.writeEnd();
	        generator.close();
	      	
	        try {
	            session.getBasicRemote().sendText(writer.toString());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
			
		
		}
	   
	   static void onGetUsers(UtilisateurFacadeRemote ufi,Session session, JsonObject jsonObject) {
			
		   	Gson gson = new Gson();
		   	
			List<Utilisateur> mesUtilisateurs = ufi.getUsers();
			String myList = gson.toJson(mesUtilisateurs);
			
			StringWriter writer = new StringWriter();
	        JsonGenerator generator = Json.createGenerator(writer);
	        generator.writeStartObject().write("listeUser",myList)
	        	.writeEnd();
	        generator.close();
	      	
	        try {
	            session.getBasicRemote().sendText(writer.toString());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
			
		
		}

}
