package ejb.bean;

import ejb.entity.Utilisateur;
import org.apache.commons.codec.binary.Hex;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.List;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless(mappedName="UtilisateurFacade")
public class UtilisateurFacadeImpl implements UtilisateurFacadeRemote{

    @PersistenceUnit(unitName="MyFactory")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager ;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Utilisateur create(Utilisateur utilisateur){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery =  entityManager.createQuery("select u from Utilisateur u where u.mail = :mail");
    	myQuery.setParameter("mail", utilisateur.getMail());
    	utilisateur.toString();
		Utilisateur u = null;
		try{
			myQuery.getSingleResult();
		}catch(NoResultException e){
			u = new Utilisateur();
			u.setMail(utilisateur.getMail());
			u.setMdp(encryptedPassword(utilisateur.getMdp()));
			u.setNom(utilisateur.getNom());
			u.setPrenom(utilisateur.getPrenom());
            u.setHashCodeToValidate(encryptedPassword(utilisateur.getNom()+utilisateur.getMail()));
            u.setValidate(utilisateur.getValidate());
			entityManager.persist(u);
		}
		entityManager.close();
		return u;
	}
  
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Utilisateur edit(Utilisateur utilisateur) {
    	entityManager = entityManagerFactory.createEntityManager();
		String newPassword = encryptedPassword(utilisateur.getMdp());

		Query query =  entityManager.createQuery("select u from Utilisateur u where u.mail = :mail AND u.mdp = :password");
		query.setParameter("mail", utilisateur.getMail());
		query.setParameter("password", newPassword);
		Utilisateur u = null;
		try{
			u = (Utilisateur) query.getSingleResult();
		}catch(NoResultException e){
			return u;
		}

		u = new Utilisateur();
		u.setMail(utilisateur.getMail());
		u.setMdp(utilisateur.getMdp());
		u.setNom(utilisateur.getNom());
		u.setPrenom(utilisateur.getPrenom());
		u.setValidate(utilisateur.getValidate());
		entityManager.merge(u);
		entityManager.close();

		return u;
    }

	public Utilisateur edit(String email, boolean validate){
		entityManager = entityManagerFactory.createEntityManager();

		Query query =  entityManager.createQuery("select u from Utilisateur u where u.mail = :mail");
		query.setParameter("mail", email);
		Utilisateur u;
		try{
			u = (Utilisateur) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}

		u.setValidate(validate);
        u.setHashCodeToValidate("1");
		entityManager.merge(u);
		entityManager.close();

		return u;
	}


	public boolean remove(Utilisateur utilisateur){
		entityManager = entityManagerFactory.createEntityManager();
		String newPassword = encryptedPassword(utilisateur.getMdp());
		if(contains(utilisateur)) {
			entityManager = entityManagerFactory.createEntityManager();
			Query myQuery = entityManager.createQuery("select u from Utilisateur u where u.mail = :mail AND u.mdp = :password");
			myQuery.setParameter("mail",utilisateur.getMail());
			myQuery.setParameter("password",newPassword);
			Utilisateur u;


			try{
				u = (Utilisateur) myQuery.getSingleResult();
				entityManager.remove(u);
				return true;
			}catch(NoResultException e){
				return false;
			}
		}

		return false;

	}

    /**
     * /!\ methode accessible que via des droits root
     * @param email
     * @return
     */
	public boolean remove(String email){
		entityManager = entityManagerFactory.createEntityManager();

		if(contains(email)) {
			entityManager = entityManagerFactory.createEntityManager();
			Query myQuery = entityManager.createQuery("select u from Utilisateur u where u.mail = :mail");
			myQuery.setParameter("mail",email);
			Utilisateur u;

			try{
				u = (Utilisateur) myQuery.getSingleResult();
				entityManager.remove(u);
                entityManager.close();
				return true;
			}catch(NoResultException e){
                entityManager.close();
				return false;
			}
		}

        entityManager.close();
		return false;
	}


    public boolean contains(Utilisateur utilisateur){
    	entityManager = entityManagerFactory.createEntityManager();
		String newPassword = encryptedPassword(utilisateur.getMdp());
    	Query myQuery = entityManager.createQuery("select u from Utilisateur u where u.mail = :mail AND u.mdp = :password");

		myQuery.setParameter("mail",utilisateur.getMail());
		myQuery.setParameter("password",newPassword);
		Utilisateur u; 
    
		try{
			u = (Utilisateur) myQuery.getSingleResult();
			System.out.println(u.toString());
			entityManager.close();
			return true;
		}catch(NoResultException e){
			entityManager.close();
			return false;
		}

    }
   
    /**
     * /!\ methode accessible que via des droits root
     * @param email
     * @return
     */
	public boolean contains(String email){
		entityManager = entityManagerFactory.createEntityManager();

		Query myQuery = entityManager.createQuery("select u from Utilisateur u where u.mail = :mail");
		myQuery.setParameter("mail",email);

		Utilisateur u;

		try{
			u = (Utilisateur) myQuery.getSingleResult();
			System.out.println(u.toString());
			entityManager.close();
			return true;
		}catch(NoResultException e){
			entityManager.close();
			return false;
		}
	}

    public boolean contains(String email, String code){
        entityManager = entityManagerFactory.createEntityManager();

        Query query =  entityManager.createQuery("select u from Utilisateur u where u.mail = :mail AND u.hashCodeToValidate = :code");
        query.setParameter("mail", email);
        query.setParameter("code", code);
        try{
            query.getSingleResult();
            entityManager.close();
            return true;
        }catch(NoResultException e){
            entityManager.close();
            return false;
        }
    }
	
	public boolean isValidated(String email){
		entityManager = entityManagerFactory.createEntityManager();

		Query myQuery = entityManager.createQuery("select u from Utilisateur u where u.mail = :mail");
		myQuery.setParameter("mail",email);

		Utilisateur u;

		try{
			u = (Utilisateur) myQuery.getSingleResult();
			return (u.getValidate());
		}catch(NoResultException e){
			entityManager.close();
			return false;
		}
	}

	
	public String printTable(){
		entityManager = entityManagerFactory.createEntityManager();

		Query myQuery = entityManager.createQuery("select u from Utilisateur u");

		List<Utilisateur> list = myQuery.getResultList();
		String result ="nb of user : "+list.size() +"\n";
		for(Utilisateur u : list){
			result = result + u.toString() +"\n";
		}

		entityManager.close();
		return result;
	}

    @SuppressWarnings("unchecked")
    public List<Utilisateur> getUsers(){
        entityManager = entityManagerFactory.createEntityManager();

        Query query =  entityManager.createQuery("select u from Utilisateur u");
        List<Utilisateur> usersList;
        try{
            usersList = query.getResultList();
        }catch(NoResultException e){
            usersList = null;
        }

        return usersList;
    }

    @SuppressWarnings("unchecked")
    public Utilisateur getUser(String email){
        entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("select u from Utilisateur u where u.mail = :mail");
        query.setParameter("mail",email);
        Utilisateur user;

        try{
            user = (Utilisateur) query.getSingleResult();
        }catch(NoResultException e){
            user = null;
        }

        return user;
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
