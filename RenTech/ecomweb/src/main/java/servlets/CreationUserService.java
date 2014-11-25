package servlets;

import java.io.IOException;
 


import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import beans.Utilisateur;
 
/** 
 * @ServerEndpoint gives the relative name for the end point
 * This will be accessed via ws://localhost:8080/EchoChamber/echo
 * Where "localhost" is the address of the host,
 * "EchoChamber" is the name of the package
 * and "echo" is the address to access this class from the server
 */
@ServerEndpoint("/CreationUserService") 
public class CreationUserService {
    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(Session session){
        System.out.println(session.getId() + " has opened a connection"); 
        try {
            session.getBasicRemote().sendText("Connection Established");
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
        
        Utilisateur utilisateur = new Utilisateur(nom,email,password);
        
        //Test si l'utilisateur existe dans la base de donnée
        
        //Si oui -> renvoi à l'utilisateur qu'il existe déja
        
        //Si non -> insertion dans la base de donnée
        
        try {
            session.getBasicRemote().sendText("Utilisateur "+nom+" crée");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void onConnectUser(Session session, JsonObject jsonObject)
    {
    	String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        
        Utilisateur utilisateur = new Utilisateur("",email,password);
        
        //Test si l'utilisateur existe dans la base de donnée
        
        //Si oui -> renvoi à l'utilisateur qu'il existe déja
        
        //Si non -> insertion dans la base de donnée
        
        try {
            session.getBasicRemote().sendText("Connexion OK avec l'email "+email);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}