package client;

/**
 * Created by augustin on 09/12/14.
 */
public interface DataBase {

    public void createUser(String name, String email, String mdp);

    public boolean deleteUser(String email);

    public boolean isUserExist(String email);

    public void printUser(String mail);

    String printTable();

    boolean checkAdmin(String name, String psw);
}