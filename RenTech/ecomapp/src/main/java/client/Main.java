package client;

/**
 * Created by augustin on 02/12/14.
 */
public class Main {

    public static void main(String[] args){
        Shell shell = new Shell(new DataBaseImpl());
        shell.run();
    }
}
