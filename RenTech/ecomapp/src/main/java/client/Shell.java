package client;

import java.util.Scanner;

/**
 * Created by augustin on 02/12/14.
 */
public class Shell {
    DataBase dataBase;
    Scanner sc;
    String root;

    public Shell(DataBase dataBase){
        this.dataBase = dataBase;
    }


    public void run(){
        sc = new Scanner(System.in);
        System.out.println("\n##################################\n" +
                           "###### Welcome in the Shell ######\n" +
                           "##################################\n");

        while(true){
            System.out.print("\n user> ");
            String command = sc.nextLine();
        }
    }

    public void runRoot(){

        while(true){
            System.out.println(root+"@root> ");
        }
    }
}
