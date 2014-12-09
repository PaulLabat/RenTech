package ejb.bean;

import java.sql.SQLException;

import ejb.entity.Utilisateur;

import javax.ejb.Remote;

/**
 * Created by augustin on 25/11/14.
 */
@Remote
public interface UtilisateurFacadeRemote {

    Utilisateur create(Utilisateur utilisateur);

    Utilisateur edit(Utilisateur utilisateur);

    boolean remove(Utilisateur utilisateur);

    boolean contains(Utilisateur utilisateur);

    boolean contains(String email);

    String printTable();
}
