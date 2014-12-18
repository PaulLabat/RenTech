package client;

import ejb.bean.*;
import ejb.entity.Commande;
import ejb.entity.Git;
import ejb.entity.Offre;
import ejb.entity.Utilisateur;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by augustin on 09/12/14.
 */
public class DataBaseImpl implements DataBase{
    UtilisateurFacadeRemote utilisateurFacadeRemote;
    AdminFacadeRemote adminFacadeRemote;
    CommandeFacadeRemote commandeFacadeRemote;
    GitFacadeRemote gitFacadeRemote;
    OffreFacadeRemote offreFacadeRemote;

    public DataBaseImpl(){
        InitialContext context;

        try {
            context = new InitialContext();
            utilisateurFacadeRemote = (UtilisateurFacadeRemote) context.lookup("UtilisateurFacade");
            adminFacadeRemote = (AdminFacadeRemote) context.lookup("AdminFacade");
            commandeFacadeRemote = (CommandeFacadeRemote) context.lookup("CommandeFacade");
            gitFacadeRemote = (GitFacadeRemote) context.lookup("GitFacade");
            offreFacadeRemote = (OffreFacadeRemote) context.lookup("OffreFacade");
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
    public Git createGit(Git git){
        return gitFacadeRemote.create(git);
    }

    @Override
    public Offre createOffre(Offre offre){
        return offreFacadeRemote.create(offre);
    }

    @Override
    public void createCommande(Commande commande){
        commandeFacadeRemote.create(commande);
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
    public String printTableUser(){
        return utilisateurFacadeRemote.printTable();
    }

    @Override
    public String printTableCommand(){
        return commandeFacadeRemote.printTable();
    }

    @Override
    public boolean checkAdmin(String name, String psw){
        return adminFacadeRemote.contains(name, psw);
    }
}
