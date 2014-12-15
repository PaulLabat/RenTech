package servlets;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.Session;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
        
        utilisateur = ufi.create(utilisateur);
       if (utilisateur!=null)
       {
    	   generator.write("status", "OK"); 
       }
       else generator.write("status", "FAIL");
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
        
        System.out.println("onConnectUser 1");
        
        StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        generator.writeStartObject();
        generator.write("fonct", "connectUser");
        //Test si l'utilisateur existe dans la base de données
        System.out.println("onConnectUser 2");

        System.out.println("UFI initialisé : "+(ufi!=null));
        if (ufi.contains(utilisateur))
        {
        	//Si oui -> renvoi à l'utilisateur qu'il existe déja
        	generator.write("status", "OK");  
        }
       else 
        {
    	   //Si non -> insertion dans la base de donnée
        	generator.write("status", "FAIL");
        }
        
        System.out.println("onConnectUser 3");
        generator.write("email", email);    
        generator.writeEnd();
        generator.close();
      
        System.out.println("onConnectUser 4");
        try {
            session.getBasicRemote().sendText(writer.toString());
        } catch (Exception ex) {
        	System.out.println("onConnectUser 5");
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
			generator.writeStartObject().write("status", "OK");
	        generator.writeStartObject().write("newEmail", newEmail);
	        generator.writeStartObject().write("newName", newName);
	        generator.writeStartObject().write("newFirstName", newFirstName);
	       
			ufi.edit(utilisateur);
			
			}
			else 
			{
				generator.writeStartObject().write("status", "FAIL");
				
			}
			//Renvoi des nouvelles infos au site 
			
	        
	        generator.writeStartObject().writeEnd();
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
				generator.writeStartObject().write("status", "OK");
				generator.writeStartObject().write("newPassword", newPassword);
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
	   
	   static void onGetUsers(UtilisateurFacadeRemote ufi,Session session, JsonObject jsonObject) {
			
		   
		   String Email = jsonObject.get("Email").getAsString();
		   Utilisateur user = ufi.getUser(Email);
		   	Gson gson = new Gson();
		   	
			String monUser = gson.toJson(user);
			
			StringWriter writer = new StringWriter();
	        JsonGenerator generator = Json.createGenerator(writer);
	        generator.writeStartObject().write("utilisateur",monUser);
	        
	        generator.close();
	      	
	        try {
	            session.getBasicRemote().sendText(writer.toString());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
			
		
		}
	   
	   static void onGetInfoUser(UtilisateurFacadeRemote ufi,Session session, JsonObject jsonObject) {
			
		   	Gson gson = new Gson();
		   	
			List<Utilisateur> mesUtilisateurs = ufi.getUsers();
			String myList = gson.toJson(mesUtilisateurs);
			
			StringWriter writer = new StringWriter();
	        JsonGenerator generator = Json.createGenerator(writer);
	        generator.writeStartObject().write("listeUser",myList);
	        
	        generator.close();
	      	
	        try {
	            session.getBasicRemote().sendText(writer.toString());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
			
		
		}

}
