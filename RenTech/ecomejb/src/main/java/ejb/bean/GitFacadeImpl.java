package ejb.bean;



import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ejb.entity.Git;

import java.util.List;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless(mappedName="GitFacade")
public class GitFacadeImpl implements GitFacadeRemote{
	
	String driver = "jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true";
	
    @PersistenceUnit(unitName="MyFactory")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager ;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Git create(Git git){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select f from Git f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
		myQuery.setParameter("tailleDisk",git.getTailleDisk());
		myQuery.setParameter("ram",git.getRam());
		myQuery.setParameter("nbreCoeur",git.getNbreCoeur());
    	git.toString();
		Git f = null;
		try{
			myQuery.getSingleResult();
		}catch(NoResultException e){
			f = new Git();
			f.setNbreCoeur(git.getNbreCoeur());
			f.setRam(git.getRam());
			f.setTailleDisk(git.getTailleDisk());
			entityManager.persist(f);
		}
		entityManager.close();
		return f;
	}
  
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Git edit(Git git) {
    	entityManager = entityManagerFactory.createEntityManager();

    	Query myQuery = entityManager.createQuery("select f from Git f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
		myQuery.setParameter("tailleDisk",git.getTailleDisk());
		myQuery.setParameter("ram",git.getRam());
		myQuery.setParameter("nbreCoeur",git.getNbreCoeur());
		Git f = null;
		try{
			f = (Git) myQuery.getSingleResult();
		}catch(NoResultException e){
			return f;
		}

		f = new Git();
		f.setNbreCoeur(git.getNbreCoeur());
		f.setRam(git.getRam());
		f.setTailleDisk(git.getTailleDisk());
		

		entityManager.merge(f);
		entityManager.close();

		return f;
    }

    public boolean remove(Git git){
    	entityManager = entityManagerFactory.createEntityManager();
    	
    	if(contains(git))
    	{
    		entityManager = entityManagerFactory.createEntityManager();
        	Query myQuery = entityManager.createQuery("select f from Git f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
    		myQuery.setParameter("tailleDisk",git.getTailleDisk());
    		myQuery.setParameter("ram",git.getRam());
    		myQuery.setParameter("nbreCoeur",git.getNbreCoeur());
    		Git f; 
    		
            
    		try{
    			f = (Git) myQuery.getSingleResult();
    		}catch(NoResultException e){
    			return false;
    		}
    		
    		entityManager.remove(f);
    	}
    		return true;
    		
    	}

    public boolean contains(Git git){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select f from Git f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
		myQuery.setParameter("tailleDisk",git.getTailleDisk());
		myQuery.setParameter("ram",git.getRam());
		myQuery.setParameter("nbreCoeur",git.getNbreCoeur());
		Git f = null; 
    
		try{
			f = (Git) myQuery.getSingleResult();
		}catch(NoResultException e){
			return false;
		}
		entityManager.close();
        return true;
    }
    
    @SuppressWarnings("unchecked")
	public List<Git> getGits(){
    	entityManager = entityManagerFactory.createEntityManager();

		Query query =  entityManager.createQuery("select f from Git f");
		List<Git> gitsList = null;
		try{
			gitsList = query.getResultList();
		}catch(NoResultException e){
			gitsList = null;
		}

		return gitsList;
	}


}
