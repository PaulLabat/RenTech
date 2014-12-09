package client;

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
        System.out.println("\n##################################\n" +
                "###### Welcome in the Shell ######\n" +
                "##################################\n" +
                "\n please enter /help if you want to see the list of command\n");

        while(!exit){
            System.out.println("rentech@user -> ");
            command = sc.nextLine();

            switch(command){
                case "/help":
                    helpUser();
                    break;
                case "/root":
                    runRoot();
                    break;
                case "/exit":
                    System.out.println("thanks you for your visiting. The system should be exited.");
                    exit = true;
                default:
                    System.out.println("unknown this command : "+command);
                    break;
            }
        }
    }

    private void runRoot(){
        boolean exit = false;
        String command;

        while(!exit){
            System.out.println("rentech@root -> ");
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
                    String email = sc.nextLine();
                    System.out.println("please enter the user password");
                    String mdp = sc.nextLine();

                    dataBase.createUser(name,email,mdp);

                    break;
                default:
                    System.out.println("unknown this command : "+command);
                    break;
            }
        }
    }

    private void helpUser(){
        System.out.println("this is the list of command wich can access with a user account");
        System.out.println("/exit --> the system will quit");
        System.out.println("/root --> use this command to pass in the root mode");
    }

    private void helpRoot(){

    }
}
