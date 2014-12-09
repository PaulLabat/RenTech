package ejb.bean;

import java.sql.SQLException;

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

    boolean contains(Utilisateur utilisateur) throws SQLException;
    
    public boolean find(Utilisateur utilisateur) throws SQLException;
    
    public void insertDB(Utilisateur utilisateur) throws SQLException;
}
