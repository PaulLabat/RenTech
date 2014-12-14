package ejb.bean;



import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ejb.entity.Commande;

import java.util.List;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless(mappedName="CommandeFacade")
public class CommandeFacadeImpl implements CommandeFacadeRemote{
	
	String driver = "jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true";
	
    @PersistenceUnit(unitName="MyFactory")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager ;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Commande create(Commande commande){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery =  entityManager.createQuery("select c from Commande c where u.adresseFactu = :adresseFactu");
    	myQuery.setParameter("mail", commande.getAdresseFactu());
		Commande c = null;
		try{
			myQuery.getSingleResult();
		}catch(NoResultException e){
			c = new Commande();
			c.setAdresseFactu(commande.getAdresseFactu());
			entityManager.persist(c);
		}
		entityManager.close();
		return c;
	}
  
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Commande edit(Commande commande) {
    	entityManager = entityManagerFactory.createEntityManager();

		Query myQuery =  entityManager.createQuery("select c from Commande c where c.adresseFactu = :adresseFactu AND c.beginDate = :password");
		myQuery.setParameter("adresseFactu",commande.getAdresseFactu());
		myQuery.setParameter("beginDate",commande.getDate());
		Commande c = null;
		try{
			c = (Commande) myQuery.getSingleResult();
		}catch(NoResultException e){
			return c;
		}

		c = new Commande();
		c.setAdresseFactu(commande.getAdresseFactu());

		entityManager.merge(c);
		entityManager.close();

		return c;
    }

    public boolean remove(Commande commande){
    	entityManager = entityManagerFactory.createEntityManager();
    	
    	if(contains(commande))
    	{
    		entityManager = entityManagerFactory.createEntityManager();
        	Query myQuery = entityManager.createQuery("select c from Commande c where c.adresseFactu = :adresseFactu AND c.beginDate = :beginDate");
    		myQuery.setParameter("adresseFactu",commande.getAdresseFactu());
    		myQuery.setParameter("beginDate",commande.getDate());
    		Commande u; 
    		
            
    		try{
    			u = (Commande) myQuery.getSingleResult();
    		}catch(NoResultException e){
    			return false;
    		}
    		
    		entityManager.remove(u);
    	}
    		return true;
    		
    	}

    public boolean contains(Commande commande){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select c from Commande c where c.adresseFactu = :adresseFactu AND c.beginDate = :beginDate");
		myQuery.setParameter("adresseFactu",commande.getAdresseFactu());
		myQuery.setParameter("beginDate",commande.getDate());
		Commande u; 
    
		try{
			u = (Commande) myQuery.getSingleResult();
			u.toString();
		}catch(NoResultException e){
			return false;
		}
		entityManager.close();
        return true;
    }
    
    @SuppressWarnings("unchecked")
	public List<Commande> getCommandes(){
    	entityManager = entityManagerFactory.createEntityManager();

		Query query =  entityManager.createQuery("select c from Commande c");
		List<Commande> ommandeList = null;
		try{
			ommandeList = query.getResultList();
		}catch(NoResultException e){
			ommandeList = null;
		}

		return ommandeList;
	}


}
