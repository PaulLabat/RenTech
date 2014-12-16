package servlets;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ejb.bean.CommandeFacadeRemote;
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
	CommandeFacadeRemote cfi; 
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
			cfi = (CommandeFacadeRemote)ctx.lookup("CommandeFacade");
			
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
        
        JsonObject jsonObject = (JsonObject)new JsonParser().parse(message);

        String fonct = jsonObject.get("fonct").getAsString();
        
        if (fonct.compareTo("connectUser")==0) ServiceUser.onConnectUser(ufi,session,jsonObject);
        else if (fonct.compareTo("createUser")==0) ServiceUser.onCreateUser(ufi,session,jsonObject);
        else if (fonct.compareTo("pushCommande")==0) onPushCommande(session,jsonObject);
        else if (fonct.compareTo("changeInfos")==0) ServiceUser.onChangeInfos(ufi,session,jsonObject);
        else if (fonct.compareTo("deleteAccount")==0) ServiceUser.onDeleteAccount(ufi,session,jsonObject);
        else if (fonct.compareTo("getUsers")==0) ServiceUser.onGetUsers(ufi,session,jsonObject);
        else if (fonct.compareTo("getInfosUser")==0) ServiceUser.onGetInfoUser(ufi,session,jsonObject);
        else if (fonct.compareTo("changePassword")==0) ServiceUser.onChangePassword(ufi,session,jsonObject);
        
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
	
	private void onPushCommande(Session session, JsonObject jsonObject) {
		String emailUser =  jsonObject.get("emailUser").getAsString();
		Gson gson = new Gson();
		//On crée la commande 
		Commande commande = new Commande();
		Utilisateur User = new Utilisateur();
		
		
		//on ajoute les nouvelles offres
		JsonArray OffreList = jsonObject.get("OffreList").getAsJsonArray();
		ArrayList<Offre> listOffre = new ArrayList<Offre>();
		
		for (int i=0; i<OffreList.size(); i++) {
			listOffre.add( gson.fromJson(OffreList.get(i),Offre.class) );
		}
		
		
		String adresseFactu = jsonObject.get("adresseFactu").getAsString();
		commande.setAdresseFactu(adresseFactu);
		commande.setOffres(listOffre);
		
		cfi.create(commande);
		
		//On renvoi la commande sur la bdd
		boolean error = false; //Boolean disant si tout s'est bien passé
        
		//Envoi de la réponse au client 
		StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        
		// Si ok 
        if (!error) 
        	{
        		generator.writeStartObject().write("status", "OK");
        		ServiceMail.sendMailCommande(User, commande);
        	}
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
