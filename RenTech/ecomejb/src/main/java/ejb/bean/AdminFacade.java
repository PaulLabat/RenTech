package ejb.bean;

/**
 * Created by Cirie on 09/12/2014.
 */
public interface AdminFacade {

    void create(String name, String psw);

    void delete(String name, String psw);

    String list();

}
