package servlets;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.persistence.OneToMany;
import javax.websocket.Session;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ejb.bean.CommandeFacadeRemote;
import ejb.bean.GitFacadeRemote;
import ejb.bean.OffreFacadeRemote;
import ejb.entity.Commande;
import ejb.entity.Forum;
import ejb.entity.Git;
import ejb.entity.Offre;
import ejb.entity.ServeurPhysique;
import ejb.entity.ServeurVirtuel;
import ejb.entity.SiteWeb;
import ejb.entity.Utilisateur;

public class ServiceCommande {
	
static void onPushCommande(CommandeFacadeRemote cfi, OffreFacadeRemote ofi,GitFacadeRemote gfi,Session session, JsonObject jsonObject) {
		
		String email = jsonObject.get("email").getAsString();
		System.out.println("email récupérée : "+email);
		String adresse = jsonObject.get("adresse").getAsString();
		System.out.println("Adresse récupérée : "+adresse);
		BigDecimal prix = jsonObject.get("prix").getAsBigDecimal();
		System.out.println("Adresse récupérée : "+prix);
	
		JsonArray git = jsonObject.get("git").getAsJsonArray();
		ArrayList<Git> Collectiongits = new ArrayList<Git>();
		Git current = null;
		for(int i=0;i<git.size();i++)
		{
			JsonObject CurrentGit = git.get(i).getAsJsonObject();
			
			current = new Git();
			current.setNbreCoeur(CurrentGit.get("nbcoeurs").getAsInt());
			current.setRam(CurrentGit.get("ram").getAsInt());
			current.setTailleDisk(CurrentGit.get("taille").getAsInt());
			System.out.println("current git : "+current.toString());
			if(!gfi.contains(current))
			{
				gfi.create(current);
			}
			Collectiongits.add(current);
		}
		
		System.out.println(Collectiongits.toString());
		
		ArrayList<ServeurPhysique> serveurPhysiques = new ArrayList<ServeurPhysique>();
		ArrayList<ServeurVirtuel> serveurVirtuels = new ArrayList<ServeurVirtuel>();	
		ArrayList<SiteWeb> siteWebs = new ArrayList<SiteWeb>();		
		ArrayList<Forum> forums = new ArrayList<Forum>();
		
		Offre currentOffre = new Offre();
		currentOffre.setPrice(prix);
		currentOffre.setForums(forums);
		currentOffre.setGits(Collectiongits);
		currentOffre.setServeurPhysiques(serveurPhysiques);
		currentOffre.setServeurVirtuels(serveurVirtuels);
		currentOffre.setSiteWebs(siteWebs);
		
		if(!ofi.contains(currentOffre)){
			ofi.create(currentOffre);
		}
		
		ArrayList<Offre> CollectionOffre = new ArrayList<Offre>();
		CollectionOffre.add(currentOffre);
		
		
		Commande currentCommande = new Commande();
		currentCommande.setAdresseFactu(adresse);
		currentCommande.setOffres(CollectionOffre);
		
		Utilisateur user = new Utilisateur();
		user.setMail(email);
		
		StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
		if (!cfi.contains(currentCommande))
		{
			System.out.println("cfi ne contient pas la commande");
			cfi.create(currentCommande);
			generator.writeStartObject().write("status", "OK")
				.writeEnd();
    		ServiceMail.sendMailCommande(user, currentCommande);
		}
        else 
        	{
        	System.out.println("cfi contient la commande");
        	generator.writeStartObject().write("status", "ERROR")
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

public static void onModifyCommande(CommandeFacadeRemote cfi, Session session,
		JsonObject jsonObject) {
	
		Utilisateur user = new Utilisateur();
		String emailUser = jsonObject.get("emailUser").getAsString();
		user.setMail(emailUser);
		String beginDate = jsonObject.get("beginDate").getAsString();
	
			Gson gson = new Gson();
			
			
			Commande currentCommande = cfi.getCommande(beginDate);
			//Envoi de la réponse au client 
			StringWriter writer = new StringWriter();
		    JsonGenerator generator = Json.createGenerator(writer);
			
			
			if (currentCommande!=null)
			{
				Commande modifiedCommande = gson.fromJson(jsonObject, Commande.class);	
				cfi.edit(modifiedCommande);
				generator.writeStartObject().write("status", "OK");
				ServiceMail.sendMailCommande(user, modifiedCommande);
				
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
