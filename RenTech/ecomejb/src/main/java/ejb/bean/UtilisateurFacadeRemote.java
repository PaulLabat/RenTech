package ejb.bean;

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

    Utilisateur edit(String email,boolean validate);

    boolean remove(Utilisateur utilisateur);

    boolean remove(String email);

    boolean contains(Utilisateur utilisateur);

    boolean contains(String email);

    boolean contains(String email, String code);
    
    public boolean isValidated(String email);

    String printTable();

    public List<Utilisateur> getUsers();

	public Utilisateur getUser(String email);

}
