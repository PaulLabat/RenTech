package client;

import ejb.bean.AdminFacadeRemote;
import ejb.bean.UtilisateurFacadeRemote;
import ejb.entity.Utilisateur;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by augustin on 09/12/14.
 */
public class DataBaseImpl implements DataBase{
    UtilisateurFacadeRemote utilisateurFacadeRemote;
    AdminFacadeRemote adminFacadeRemote;

    public DataBaseImpl(){
        InitialContext context;

        try {
            context = new InitialContext();
            utilisateurFacadeRemote = (UtilisateurFacadeRemote) context.lookup("UtilisateurFacade");
            adminFacadeRemote = (AdminFacadeRemote) context.lookup("AdminFacade");
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void createUser(String name, String email, String mdp) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMdp(mdp);
        utilisateur.setMail(email);
        utilisateur.setNom(name);
        utilisateurFacadeRemote.create(utilisateur);
    }

    @Override
    public void createUser(String name, String email, String mdp, boolean isValidate){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMdp(mdp);
        utilisateur.setMail(email);
        utilisateur.setNom(name);
        utilisateur.setValidate(isValidate);
        utilisateurFacadeRemote.create(utilisateur);
    }

    @Override
    public boolean deleteUser(String email) {
        return utilisateurFacadeRemote.remove(email);
    }

    @Override
    public boolean isUserExist(String email) {
       return utilisateurFacadeRemote.contains(email);
    }

    @Override
    public void printUser(String mail) {

    }

    @Override
    public String printTable(){
        return utilisateurFacadeRemote.printTable();
    }

    @Override
    public boolean checkAdmin(String name, String psw){
        return adminFacadeRemote.contains(name, psw);
    }
}
