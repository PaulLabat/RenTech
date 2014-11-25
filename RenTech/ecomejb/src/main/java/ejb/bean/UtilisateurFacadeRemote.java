package ejb.bean;

import ejb.entity.Utilisateur;

import javax.ejb.Remote;

/**
 * Created by augustin on 25/11/14.
 */
@Remote
public interface UtilisateurFacadeRemote {

    void create(Utilisateur utilisateur);

    void edit(Utilisateur utilisateur);

    void remove(Utilisateur utilisateur);

}
