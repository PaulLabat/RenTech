package client;

import ejb.bean.UtilisateurFacadeRemote;
import ejb.entity.Utilisateur;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by augustin on 09/12/14.
 */
public class DataBaseImpl implements DataBase{
    UtilisateurFacadeRemote utilisateurFacadeRemote;

    public DataBaseImpl(){
        InitialContext context;

        try {
            context = new InitialContext();
            utilisateurFacadeRemote = (UtilisateurFacadeRemote) context.lookup("UtilisateurFacade");
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void createUser(String name, String email, String mdp) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMdpAndEncrypt(mdp);
        utilisateur.setMail(email);
        utilisateur.setNom(name);
        utilisateurFacadeRemote.create(utilisateur);
    }

    @Override
    public void deleteUser(String email) {
        
    }

    @Override
    public boolean isUserExist(String email) {
        return false;
    }

    @Override
    public void printUser(String mail) {

    }
}
