package ejb.bean;

import ejb.entity.Utilisateur;

import javax.ejb.Local;

/**
 * Created by augustin on 25/11/14.
 */

@Local
public interface UtilisateurFacadeLocal {

    void create(Utilisateur utilisateur);

    void edit(Utilisateur utilisateur);

    void remove(Utilisateur utilisateur);
}
