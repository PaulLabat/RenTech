package ejb.bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by Cirie on 09/12/2014.
 */
public class AdminFacadeImpl implements AdminFacade{

    @PersistenceUnit(unitName="MyFactory")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager ;

    @Override
    public void create(String name, String psw) {

    }

    @Override
    public void delete(String name, String psw) {

    }

    @Override
    public String list() {
        return null;
    }
}
