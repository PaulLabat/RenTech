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
                    runRoot();
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
                case "/creat":
                    System.out.println("please enter the user name");
                    String name = sc.nextLine();
                    System.out.println("please enter the user email");
                    email = sc.nextLine();
                    System.out.println("please enter the user password");
                    String mdp = sc.nextLine();

                    dataBase.createUser(name,email,mdp);
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

    private void helpUser(){
        System.out.println("this is the list of command wich can access with a user account");
        System.out.println("/exit --> the system will quit");
        System.out.println("/root --> use this command to pass in the root mode");
    }

    private void helpRoot(){

    }
}
