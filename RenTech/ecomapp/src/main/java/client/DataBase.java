package client;

import ejb.entity.Commande;
import ejb.entity.Git;
import ejb.entity.Offre;

/**
 * Created by augustin on 09/12/14.
 */
public interface DataBase {

    public void createUser(String name, String email, String mdp);

    public void createUser(String name, String email, String mdp, boolean isValidate);

    Git createGit(Git git);

    Offre createOffre(Offre offre);

    void createCommande(Commande commande);

    public boolean deleteUser(String email);

    public boolean isUserExist(String email);

    public void printUser(String mail);

    String printTableUser();

    String printTableCommand();

    boolean checkAdmin(String name, String psw);
}