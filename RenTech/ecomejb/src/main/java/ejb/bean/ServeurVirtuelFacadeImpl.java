package ejb.bean;



import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ejb.entity.ServeurVirtuel;

import java.util.List;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless(mappedName="ServeurVirtuelFacade")
public class ServeurVirtuelFacadeImpl implements ServeurVirtuelFacadeRemote{
	
	String driver = "jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true";
	
    @PersistenceUnit(unitName="MyFactory")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager ;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ServeurVirtuel create(ServeurVirtuel serveurVirtuel){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select f from ServeurVirtuel f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
		myQuery.setParameter("tailleDisk",serveurVirtuel.getTailleDisk());
		myQuery.setParameter("ram",serveurVirtuel.getRam());
		myQuery.setParameter("nbreCoeur",serveurVirtuel.getNbreCoeur());
    	serveurVirtuel.toString();
		ServeurVirtuel f = null;
		try{
			myQuery.getSingleResult();
		}catch(NoResultException e){
			f = new ServeurVirtuel();
			f.setNbreCoeur(serveurVirtuel.getNbreCoeur());
			f.setRam(serveurVirtuel.getRam());
			f.setTailleDisk(serveurVirtuel.getTailleDisk());
			entityManager.persist(f);
		}
		entityManager.close();
		return f;
	}
  
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ServeurVirtuel edit(ServeurVirtuel serveurVirtuel) {
    	entityManager = entityManagerFactory.createEntityManager();

    	Query myQuery = entityManager.createQuery("select f from ServeurVirtuel f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
		myQuery.setParameter("tailleDisk",serveurVirtuel.getTailleDisk());
		myQuery.setParameter("ram",serveurVirtuel.getRam());
		myQuery.setParameter("nbreCoeur",serveurVirtuel.getNbreCoeur());
		ServeurVirtuel f = null;
		try{
			f = (ServeurVirtuel) myQuery.getSingleResult();
		}catch(NoResultException e){
			return f;
		}

		f = new ServeurVirtuel();
		f.setNbreCoeur(serveurVirtuel.getNbreCoeur());
		f.setRam(serveurVirtuel.getRam());
		f.setTailleDisk(serveurVirtuel.getTailleDisk());
		

		entityManager.merge(f);
		entityManager.close();

		return f;
    }

    public boolean remove(ServeurVirtuel serveurVirtuel){
    	entityManager = entityManagerFactory.createEntityManager();
    	
    	if(contains(serveurVirtuel))
    	{
    		entityManager = entityManagerFactory.createEntityManager();
        	Query myQuery = entityManager.createQuery("select f from ServeurVirtuel f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
    		myQuery.setParameter("tailleDisk",serveurVirtuel.getTailleDisk());
    		myQuery.setParameter("ram",serveurVirtuel.getRam());
    		myQuery.setParameter("nbreCoeur",serveurVirtuel.getNbreCoeur());
    		ServeurVirtuel f; 
    		
            
    		try{
    			f = (ServeurVirtuel) myQuery.getSingleResult();
    		}catch(NoResultException e){
    			return false;
    		}
    		
    		entityManager.remove(f);
    	}
    		return true;
    		
    	}

    public boolean contains(ServeurVirtuel serveurVirtuel){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select f from ServeurVirtuel f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
		myQuery.setParameter("tailleDisk",serveurVirtuel.getTailleDisk());
		myQuery.setParameter("ram",serveurVirtuel.getRam());
		myQuery.setParameter("nbreCoeur",serveurVirtuel.getNbreCoeur());
		ServeurVirtuel f = null; 
    
		try{
			f = (ServeurVirtuel) myQuery.getSingleResult();
		}catch(NoResultException e){
			return false;
		}
		entityManager.close();
        return true;
    }
    
    @SuppressWarnings("unchecked")
	public List<ServeurVirtuel> getServeurVirtuels(){
    	entityManager = entityManagerFactory.createEntityManager();

		Query query =  entityManager.createQuery("select f from ServeurVirtuel f");
		List<ServeurVirtuel> serveurVirtuelsList = null;
		try{
			serveurVirtuelsList = query.getResultList();
		}catch(NoResultException e){
			serveurVirtuelsList = null;
		}

		return serveurVirtuelsList;
	}


}
