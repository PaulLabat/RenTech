package ejb.bean;

import ejb.entity.Utilisateur;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless(mappedName="UtilisateurFacade")
public class UtilisateurFacadeImpl implements UtilisateurFacadeRemote{
	
	String driver = "jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true";
	
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
			u.setMdp(utilisateur.getMdp());
			u.setNom(utilisateur.getNom());
			u.setPrenom(utilisateur.getPrenom());
			entityManager.persist(u);
		}
		entityManager.close();
		return u;
	}
  
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Utilisateur edit(Utilisateur utilisateur) {
    	entityManager = entityManagerFactory.createEntityManager();

		Query query =  entityManager.createQuery("select u from Utilisateur u where u.mail = :mail AND u.mdp = :password");
		query.setParameter("mail", utilisateur.getMail());
		query.setParameter("password", utilisateur.getMdp());
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

		entityManager.merge(u);
		entityManager.close();

		return u;
    }

    public boolean remove(Utilisateur utilisateur){
    	entityManager = entityManagerFactory.createEntityManager();
    	
    	if(contains(utilisateur))
    	{
    		entityManager = entityManagerFactory.createEntityManager();
        	Query myQuery = entityManager.createQuery("select u from Utilisateur u where u.mail = :mail AND u.mdp = :password");
    		myQuery.setParameter("mail",utilisateur.getMail());
    		myQuery.setParameter("password",utilisateur.getMdp());
    		Utilisateur u; 
    		
            
    		try{
    			u = (Utilisateur) myQuery.getSingleResult();
    		}catch(NoResultException e){
    			return false;
    		}
    		
    		entityManager.remove(u);
    	}
    		return true;
    		
    		
    	}

    public boolean contains(Utilisateur utilisateur){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select u from Utilisateur u where u.mail = :mail AND u.mdp = :password");
		myQuery.setParameter("mail",utilisateur.getMail());
		myQuery.setParameter("password",utilisateur.getMdp());
		Utilisateur u; 
    
		try{
			u = (Utilisateur) myQuery.getSingleResult();
			u.toString();
		}catch(NoResultException e){
			return false;
		}
		entityManager.close();
        return true;
    }
}
