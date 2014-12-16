package servlets;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.Session;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ejb.bean.CommandeFacadeRemote;
import ejb.entity.Commande;
import ejb.entity.Utilisateur;

public class ServiceCommande {
	
static void onPushCommande(CommandeFacadeRemote cfi,Session session, JsonObject jsonObject) {
		
		Gson gson = new Gson();
		//On crée la commande 
		Commande commande = gson.fromJson(jsonObject, Commande.class);	

		Utilisateur user = new Utilisateur();
		String emailUser = jsonObject.get("emailUser").getAsString();
		user.setMail(emailUser);
		

        
		//Envoi de la réponse au client 
		StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        
		// Si ok 
		if (!cfi.contains(commande))
		{
			cfi.create(commande);
			generator.writeStartObject().write("status", "OK");
    		ServiceMail.sendMailCommande(user, commande);
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
