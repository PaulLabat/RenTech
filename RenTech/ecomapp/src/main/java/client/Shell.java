package client;

import ejb.entity.Commande;
import ejb.entity.Git;
import ejb.entity.Offre;
import org.apache.commons.lang.RandomStringUtils;

import java.io.Console;
import java.util.Scanner;

/**
 * Created by augustin on 02/12/14.
 */
public class Shell {
    DataBase dataBase;
    Scanner sc;

    public Shell(DataBase dataBase){
        this.dataBase = dataBase;
    }


    public void run(){
        sc = new Scanner(System.in);
        boolean exit = false;
        String command;
        System.out.println("\n\t\t##################################\n" +
                "\t\t###### Welcome in the Shell ######\n" +
                "\t\t##################################\n" +
                "\n\t please enter /help if you want to see the list of command\n");

        while(!exit){
            System.out.print("rentech@user -> ");
            command = sc.nextLine();

            switch(command){
                case "/help":
                    helpUser();
                    break;
                case "/root":
                    Console console = System.console();
                    System.out.println("please enter the root name");
                    String user = sc.nextLine();
                    String password = new String(console.readPassword("Please enter your password: "));
                    if(dataBase.checkAdmin(user,password)){
                        System.out.println("access to the root mode");
                        runRoot();
                    } else{
                        System.out.println("access denied");
                    }
                    break;
                case "/exit":
                    System.out.println("thanks you for your visiting. The system should be exited.");
                    exit = true;
                    break;
                default:
                    System.out.println("unknown this command : "+command);
                    break;
            }
        }
    }

    private void runRoot(){
        boolean exit = false;
        String command;
        String email;

        while(!exit){
            System.out.print("rentech@root -> ");
            command = sc.nextLine();

            switch (command){
                case "/help":
                    helpRoot();
                    break;
                case "/exit":
                    exit = true;
                    System.out.println("exit root mode");
                    break;
                case "/create":
                    System.out.println("please enter the user name");
                    String name = sc.nextLine();
                    System.out.println("please enter the user email");
                    email = sc.nextLine();
                    System.out.println("please enter the user password");
                    String mdp = sc.nextLine();
                    System.out.println("Do you want to validate this account (y/n) ?");
                    String validate = sc.nextLine();
                    boolean isValidate;
                    if(validate.contains("y")){
                        isValidate = true;
                        System.out.println("validate account");
                    } else{
                        isValidate = false;
                        System.out.println("Invalid account");
                    }
                    dataBase.createUser(name,email,mdp,isValidate);
                    break;
                case "/generate":
                    System.out.println("please enter the number of user that i have to generate for you");
                    try {
                        int nbUser = Integer.parseInt(sc.nextLine());
                        generateMultiUser(nbUser);
                    } catch (Exception ex){
                        System.out.println("you should enter a number, not anything");
                    }

                    break;
                case "/generateCommand":
                    try{
                        generateCommand();
                    }catch (Exception ex){
                        System.out.println("failed when you would created a command " +ex);
                    }
                    break;
                case "/delete":
                    System.out.println("please enter the user email");
                    email = sc.nextLine();
                    if(dataBase.deleteUser(email)){
                        System.out.println("successfully deleted");
                    } else{
                        System.out.println("error when try to delete user, maybe user doesn't exist ?");
                    }
                    break;
                case "/search":
                    System.out.println("please enter the user email");
                    email = sc.nextLine();
                    if(dataBase.isUserExist(email)){
                        System.out.println("this user exist");
                    } else{
                        System.out.println("this user doesn't exist");
                    }
                    break;
                case "/list":
                    System.out.println(dataBase.printTable());
                    break;
                default:
                    System.out.println("unknown this command : "+command);
                    break;
            }
        }
    }

    private void generateMultiUser(int nbUser){
        int i = 0;
        String[] listMail = {"gmail.com", "laposte.net", "rentech.com", "hotmail.fr", "outlook.com"," e.ujf-grenoble.fr"};
        int size = listMail.length;
        String nom;
        String prenom;
        String password;
        String email;

        while(i < nbUser){
            nom = RandomStringUtils.random(5,true,false).toLowerCase();
            prenom = RandomStringUtils.random(5,true,false).toLowerCase();
            password = RandomStringUtils.random(14,true,true);
            email = prenom + "." + nom + "@" + listMail[i%size];

            try {
                System.out.println("the user account will be created with parameters : name --> " + nom + " email--> " + email + "  password : " + password);
                dataBase.createUser(nom, email, password);
            } catch (Exception ex){
                System.out.println("An exception appeared during a creation of account: "+ex);
            }

            i++;
        }
    }

    private void generateCommand(){
        String adresse = "93 Avenue Jean Perrot 38000 Grenoble";
        Git git = new Git();
        git.setNbreCoeur(1);
        git.setRam(4);
        git.setTailleDisk(25);
        git = dataBase.createGit(git);

        Offre offre = new Offre();
        offre.addGit(git);
        offre.setPrice(4.99);
        offre = dataBase.createOffre(offre);

        Commande commande = new Commande();
        commande.setAdresseFactu(adresse);
        commande.addOffre(offre);
        dataBase.createCommande(commande);
    }

    private void helpUser(){
        System.out.println("this is the list of command wich can access with a user account");
        System.out.println("/exit --> the system will quit");
        System.out.println("/root --> use this command to pass in the root mode");
    }

    private void helpRoot(){
        System.out.println("this is the list of command wich can access with a root account");
        System.out.println("/exit ---> return to the user mode\n" +
                "/create ---> create an user acoount \n" +
                "/generate ---> generate x user accounts \n" +
                "/generateCommand ---> Generate one command\n"+
                "/delete ----> delete an user account \n" +
                "/search ---> see if a user account exist\n" +
                "/list ---> show the list of user account\n");
    }
}
