package ejb.bean;

import javax.ejb.Remote;

/**
 * Created by Cirie on 09/12/2014.
 */
@Remote
public interface AdminFacade {

    void create(String name, String psw);

    void delete(String name, String psw);

    boolean contains(String name, String password);

    String list();

}
