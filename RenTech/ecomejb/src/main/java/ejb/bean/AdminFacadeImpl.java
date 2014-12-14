package ejb.bean;

import org.apache.commons.codec.binary.Hex;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Cirie on 09/12/2014.
 */
@Stateless(mappedName="AdminFacade")
public class AdminFacadeImpl implements AdminFacadeRemote {

    @PersistenceUnit(unitName="MyFactory")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager ;

    @Override
    public void create(String name, String psw) {

    }

    @Override
    public void delete(String name, String psw) {
        String newPassword = encryptedPassword(psw);
    }

    @Override
    public boolean contains(String name, String password) {
        String newPassword = encryptedPassword(password);
        if(newPassword != null){
            entityManager = entityManagerFactory.createEntityManager();
            Query myQuery = entityManager.createQuery("select a from Admin a where a.nom = :nom AND a.mdp = :password");
            myQuery.setParameter("nom",name);
            myQuery.setParameter("password",newPassword);

            try{
                myQuery.getSingleResult();
                entityManager.close();
                return true;
            }catch(NoResultException e){
                entityManager.close();
                return false;
            }
        }
        return false;
    }

    @Override
    public String list() {
        return null;
    }

    private String encryptedPassword(String password){
        String newPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            newPassword = new String(Hex.encodeHex(md.digest(password.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return newPassword;
    }
}
