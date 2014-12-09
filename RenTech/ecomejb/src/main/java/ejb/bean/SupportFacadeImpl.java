package ejb.bean;



import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ejb.entity.Entreprise;
import ejb.entity.Support;

import java.util.List;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless(mappedName="supportFacade")
public class SupportFacadeImpl implements SupportFacadeRemote{
	
	String driver = "jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true";
	
    @PersistenceUnit(unitName="MyFactory")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager ;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Support create(Support support){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery =  entityManager.createQuery("select u from support u where u.mail = :mail");
    	//myQuery.setParameter("mail", support.getMail());
    	support.toString();
		Support s = null;
		try{
			myQuery.getSingleResult();
		}catch(NoResultException e){
			/*u = new support();
			u.setMail(support.getMail());
			u.setMdp(support.getMdp());
			u.setNom(support.getNom());
			u.setPrenom(support.getPrenom());*/
			entityManager.persist(s);
		}
		entityManager.close();
		return s;
	}
  
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Support edit(Support support) {
    	entityManager = entityManagerFactory.createEntityManager();

		Query query =  entityManager.createQuery("select u from support u where u.mail = :mail AND u.mdp = :password");
		/*query.setParameter("mail", support.getMail());
		query.setParameter("password", support.getMdp());*/
		Support s = null;
		try{
			s = (Support) query.getSingleResult();
		}catch(NoResultException e){
			return s;
		}

		s = new Support();
		/*u.setMail(support.getMail());
		u.setMdp(support.getMdp());
		u.setNom(support.getNom());
		u.setPrenom(support.getPrenom());*/

		entityManager.merge(s);
		entityManager.close();

		return s;
    }

    public boolean contains(Support support){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select e from Support e where e.nom = :nom");
		//myQuery.setParameter("nom",support.getNom());
		Support s; 
    
		try{
			s = (Support) myQuery.getSingleResult();
			s.toString();
		}catch(NoResultException e){
			return false;
		}
		entityManager.close();
        return true;
    }
    
    public boolean remove(Support support){
    	entityManager = entityManagerFactory.createEntityManager();
    	
    	if(contains(support))
    	{
    		entityManager = entityManagerFactory.createEntityManager();
        	Query myQuery = entityManager.createQuery("select u from support u where u.mail = :mail AND u.mdp = :password");
    		/*myQuery.setParameter("mail",support.getMail());
    		myQuery.setParameter("password",support.getMdp());*/
    		Support s; 
    		
            
    		try{
    			s = (Support) myQuery.getSingleResult();
    		}catch(NoResultException e){
    			return false;
    		}
    		
    		entityManager.remove(s);
    	}
    		return true;
    		
    	}

    
    @SuppressWarnings("unchecked")
	public List<Support> getSupports(){
    	entityManager = entityManagerFactory.createEntityManager();

		Query query =  entityManager.createQuery("select u from support u");
		List<Support> supportList = null;
		try{
			supportList = query.getResultList();
		}catch(NoResultException e){
			supportList = null;
		}

		return supportList;
	}


}
