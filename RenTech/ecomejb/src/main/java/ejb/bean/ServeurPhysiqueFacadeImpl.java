package ejb.bean;



import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ejb.entity.ServeurPhysique;

import java.util.List;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless(mappedName="ServeurPhysiqueFacade")
public class ServeurPhysiqueFacadeImpl implements ServeurPhysiqueFacadeRemote{
	
	String driver = "jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true";
	
    @PersistenceUnit(unitName="MyFactory")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager ;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ServeurPhysique create(ServeurPhysique serveurPhysique){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select f from ServeurPhysique f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
		myQuery.setParameter("tailleDisk",serveurPhysique.getTailleDisk());
		myQuery.setParameter("ram",serveurPhysique.getRam());
		myQuery.setParameter("nbreCoeur",serveurPhysique.getNbreCoeur());
    	serveurPhysique.toString();
		ServeurPhysique f = null;
		try{
			myQuery.getSingleResult();
		}catch(NoResultException e){
			f = new ServeurPhysique();
			f.setNbreCoeur(serveurPhysique.getNbreCoeur());
			f.setRam(serveurPhysique.getRam());
			f.setTailleDisk(serveurPhysique.getTailleDisk());
			entityManager.persist(f);
		}
		entityManager.close();
		return f;
	}
  
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ServeurPhysique edit(ServeurPhysique serveurPhysique) {
    	entityManager = entityManagerFactory.createEntityManager();

    	Query myQuery = entityManager.createQuery("select f from ServeurPhysique f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
		myQuery.setParameter("tailleDisk",serveurPhysique.getTailleDisk());
		myQuery.setParameter("ram",serveurPhysique.getRam());
		myQuery.setParameter("nbreCoeur",serveurPhysique.getNbreCoeur());
		ServeurPhysique f = null;
		try{
			f = (ServeurPhysique) myQuery.getSingleResult();
		}catch(NoResultException e){
			return f;
		}

		f = new ServeurPhysique();
		f.setNbreCoeur(serveurPhysique.getNbreCoeur());
		f.setRam(serveurPhysique.getRam());
		f.setTailleDisk(serveurPhysique.getTailleDisk());
		

		entityManager.merge(f);
		entityManager.close();

		return f;
    }

    public boolean remove(ServeurPhysique serveurPhysique){
    	entityManager = entityManagerFactory.createEntityManager();
    	
    	if(contains(serveurPhysique))
    	{
    		entityManager = entityManagerFactory.createEntityManager();
        	Query myQuery = entityManager.createQuery("select f from ServeurPhysique f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
    		myQuery.setParameter("tailleDisk",serveurPhysique.getTailleDisk());
    		myQuery.setParameter("ram",serveurPhysique.getRam());
    		myQuery.setParameter("nbreCoeur",serveurPhysique.getNbreCoeur());
    		ServeurPhysique f; 
    		
            
    		try{
    			f = (ServeurPhysique) myQuery.getSingleResult();
    		}catch(NoResultException e){
    			return false;
    		}
    		
    		entityManager.remove(f);
    	}
    		return true;
    		
    	}

    public boolean contains(ServeurPhysique serveurPhysique){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select f from ServeurPhysique f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
		myQuery.setParameter("tailleDisk",serveurPhysique.getTailleDisk());
		myQuery.setParameter("ram",serveurPhysique.getRam());
		myQuery.setParameter("nbreCoeur",serveurPhysique.getNbreCoeur());
		ServeurPhysique f = null; 
    
		try{
			f = (ServeurPhysique) myQuery.getSingleResult();
		}catch(NoResultException e){
			return false;
		}
		entityManager.close();
        return true;
    }
    
    @SuppressWarnings("unchecked")
	public List<ServeurPhysique> getServeurPhysiques(){
    	entityManager = entityManagerFactory.createEntityManager();

		Query query =  entityManager.createQuery("select f from ServeurPhysique f");
		List<ServeurPhysique> serveurPhysiquesList = null;
		try{
			serveurPhysiquesList = query.getResultList();
		}catch(NoResultException e){
			serveurPhysiquesList = null;
		}

		return serveurPhysiquesList;
	}


}
