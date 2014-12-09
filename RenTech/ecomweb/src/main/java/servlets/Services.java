package servlets;

import java.io.IOException;


import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.EJB;


import ejb.bean.UtilisateurFacadeImpl;
import ejb.bean.UtilisateurFacadeRemote;
import ejb.entity.Commande;
import ejb.entity.Offre;
import ejb.entity.Utilisateur;
 
/** 
 * @ServerEndpoint gives the relative name for the end point
 * This will be accessed via ws://localhost:8080/EchoChamber/echo
 * Where "localhost" is the address of the host,
 * "EchoChamber" is the name of the package
 * and "echo" is the address to access this class from the server
 */
@ServerEndpoint("/Services") 
public class Services {
	


	UtilisateurFacadeRemote ufi; 
	InitialContext ctx;

    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(Session session){
    	String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    	try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			ctx = new InitialContext(System.getProperties());
			System.out.println("CTX initialisé");
			ufi = (UtilisateurFacadeRemote)ctx.lookup("UtilisateurFacade");
			
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
        System.out.println(session.getId() + " has opened a connection"); 
    	StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        generator.writeStartObject()
            .write("status", "Connexion Established")
            .writeEnd();
        generator.close();
        
        
        try {
            session.getBasicRemote().sendText(writer.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 
    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     * @throws SQLException 
     */
    @OnMessage
    public void onMessage(String message, Session session) throws SQLException{
        System.out.println("Message from " + session.getId() + ": " +message);
        JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
        String fonct = jsonObject.getString("fonct");
        
        if (fonct.compareTo("connectUser")==0) onConnectUser(session,jsonObject);
        else if (fonct.compareTo("createUser")==0) onCreateUser(session,jsonObject);
        else if (fonct.compareTo("pushCommande")==0) onPushCommande(session,jsonObject);
        else if (fonct.compareTo("changeInfos")==0) onChangeInfos(session,jsonObject);
        else if (fonct.compareTo("deleteAccount")==0) onDeleteAccount(session,jsonObject);
        else if (fonct.compareTo("onPushCommande")==0) onPushCommande(session,jsonObject);
        
    }

	/**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){
        System.out.println("Session " +session.getId()+" has ended");
    }
    
    
   public void onCreateUser(Session session, JsonObject jsonObject)
    {
    	String nom = jsonObject.getString("name");
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        
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
    
    public void onConnectUser(Session session, JsonObject jsonObject) throws SQLException
    {
    
    	
    	System.out.println("Debut onConnectUser");
    	String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        
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
    
    
   private void onDeleteAccount(Session session, JsonObject jsonObject) {
    	String email = jsonObject.getString("email");
    	
    	System.out.println("On supprime le compte ayant l'email : "+email);
    	Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMail(email);
        
    	//Suppression du compte sur la bdd
    	ufi.remove(utilisateur);
    	
		
	}

	private void onChangeInfos(Session session, JsonObject jsonObject) {
		String oldEmail = jsonObject.getString("oldEmail");
		String newEmail = jsonObject.getString("newEmail");
		String newName = jsonObject.getString("newName");
		String newFirstName = jsonObject.getString("newFirstName");
		
		//On push les infos sur l'utilisateur ayant pour email oldEmail et on remplace les infos par newEmail, newName
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setMail(oldEmail);
		//utilisateur = ufi.find(utilisateur);
		utilisateur.setMail(newEmail);
		utilisateur.setNom(newName);
		utilisateur.setPrenom(newFirstName);
		
		ufi.edit(utilisateur);
		
		//Renvoi des nouvelles infos au site 
		StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        generator.writeStartObject().write("status", "OK");
        generator.writeStartObject().write("newEmail", newEmail);
        generator.writeStartObject().write("newName", newName);
        generator.writeStartObject().write("newFirstName", newFirstName);
        generator.writeStartObject().writeEnd();
        generator.close();
      	
        try {
            session.getBasicRemote().sendText(writer.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
		
	}
	
	private void onPushCommande(Session session, JsonObject jsonObject) {
		String emailUser =  jsonObject.getString("emailUser");
		
		//On crée la commande 
		Commande commande = new Commande();
				
		//on ajoute les nouvelles offres
		JsonArray OffreList = jsonObject.getJsonArray("OffreList");
		ArrayList<Offre> listOffre = new ArrayList<Offre>();
		int offreID;
		for (int i=0; i<OffreList.size(); i++) {
			offreID = OffreList.getJsonObject(i).getInt("offreID");
			//Récupération de l'offre correspondante sur la bdd;
			Offre offre = new Offre();
			//offre = find(id);
			listOffre.add(offre);
		}
		
		String adresseFactu = jsonObject.getString("adresseFactu");
		commande.setAdresseFactu(adresseFactu);
		commande.setOffres(listOffre);
		
		//On renvoi la commande sur la bdd
		boolean error = false; //Boolean disant si tout s'est bien passé
        
		//Envoi de la réponse au client 
		StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        
		// Si ok 
        if (!error) generator.writeStartObject().write("status", "OK");
        else generator.writeStartObject().write("status", "ERROR");
		//Renvoi des nouvelles infos au site 
        generator.writeStartObject().writeEnd();
        generator.close();
      	
        try {
            session.getBasicRemote().sendText(writer.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		

	}
	
}
