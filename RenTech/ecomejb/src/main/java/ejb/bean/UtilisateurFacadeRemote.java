package ejb.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import ejb.entity.Utilisateur;

/**
 * Created by augustin on 25/11/14.
 */
@Remote
public interface UtilisateurFacadeRemote {

    Utilisateur create(Utilisateur utilisateur);

    Utilisateur edit(Utilisateur utilisateur);

    boolean remove(Utilisateur utilisateur);

    boolean contains(Utilisateur utilisateur);
    
    public List<Utilisateur> getUsers();
}
