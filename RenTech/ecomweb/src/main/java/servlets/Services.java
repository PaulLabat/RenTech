package servlets;

import java.awt.List;
import java.io.IOException;
 


import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import ejb.entity.Commande;
import ejb.entity.Forum;
import ejb.entity.Git;
import ejb.entity.Offre;
import ejb.entity.ServeurPhysique;
import ejb.entity.ServeurVirtuel;
import ejb.entity.SiteWeb;
import ejb.entity.Support;
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
	

    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(Session session){
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
     */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("Message from " + session.getId() + ": " +message);
        JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
        String fonct = jsonObject.getString("fonct");
        
        if (fonct.compareTo("createUser")==0) onCreateUser(session,jsonObject);
        else if (fonct.compareTo("connectUser")==0) onConnectUser(session,jsonObject);
        else if (fonct.compareTo("pushCommande")==0) onPushCommande(session,jsonObject);
        else if (fonct.compareTo("changeInfos")==0) onChangeInfos(session,jsonObject);
        else if (fonct.compareTo("deleteAccount")==0) onDeleteAccount(session,jsonObject);
        
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
        
       // UtilisateurFacadeImpl UFI = new UtilisateurFacadeImpl();
        
        //UFI.create(utilisateur);
        //Test si l'utilisateur existe dans la base de donnée
        
        //Si oui -> renvoi à l'utilisateur qu'il existe déja
        
        //Si non -> insertion dans la base de donnée
    	StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        generator.writeStartObject()
            .write("status", "OK")
            .write("nom", nom)
            .write("email", email)
            .write("password", password)
            .writeEnd();
        generator.close();
        
        try {
            session.getBasicRemote().sendText(writer.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void onConnectUser(Session session, JsonObject jsonObject)
    {
    	String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMail(email);
        utilisateur.setMdp(password);
        
        //Test si l'utilisateur existe dans la base de donnée
        
        //Si oui -> renvoi à l'utilisateur qu'il existe déja
        
        //Si non -> insertion dans la base de donnée
    	StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        generator.writeStartObject()
            .write("status", "OK")
            .write("email", email)
            .writeEnd();
        generator.close();
      
        try {
            session.getBasicRemote().sendText(writer.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void onAddCart(Session session, JsonObject jsonObject)
    {
    	String ID = jsonObject.getString("idObjet");
        
    	System.out.println("On ajoute au panier :"+ID);
    	
    	StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        generator.writeStartObject()
            .write("status", "OK")
            .write("idObjet",ID)
            .writeEnd();
        generator.close();
      	
        try {
        	
            session.getBasicRemote().sendText(writer.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void onDeleteAccount(Session session, JsonObject jsonObject) {
    	String email = jsonObject.getString("email");
    	
    	System.out.println("On supprime le compte ayant l'email : "+email);
    	
    	//Suppression du compte sur la bdd
    	
    	
		
	}

	private void onChangeInfos(Session session, JsonObject jsonObject) {
		String oldEmail = jsonObject.getString("oldEmail");
		String newEmail = jsonObject.getString("newEmail");
		String newName = jsonObject.getString("newName");
		String newFirstName = jsonObject.getString("newFirstName");
		
		//On push les infos sur l'utilisateur ayant pour email oldEmail et on remplace les infos par newEmail, newName
		
		
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
			offre = find(id);
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
