package ejb.bean;



import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ejb.entity.Forum;

import java.util.List;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless(mappedName="ForumFacade")
public class ForumFacadeImpl implements ForumFacadeRemote{
	
	String driver = "jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true";
	
    @PersistenceUnit(unitName="MyFactory")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager ;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Forum create(Forum forum){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select f from Forum f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
		myQuery.setParameter("tailleDisk",forum.getTailleDisk());
		myQuery.setParameter("ram",forum.getRam());
		myQuery.setParameter("nbreCoeur",forum.getNbreCoeur());
    	forum.toString();
		Forum f = null;
		try{
			myQuery.getSingleResult();
		}catch(NoResultException e){
			f = new Forum();
			f.setNbreCoeur(forum.getNbreCoeur());
			f.setRam(forum.getRam());
			f.setTailleDisk(forum.getTailleDisk());
			entityManager.persist(f);
		}
		entityManager.close();
		return f;
	}
  
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Forum edit(Forum forum) {
    	entityManager = entityManagerFactory.createEntityManager();

    	Query myQuery = entityManager.createQuery("select f from Forum f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
		myQuery.setParameter("tailleDisk",forum.getTailleDisk());
		myQuery.setParameter("ram",forum.getRam());
		myQuery.setParameter("nbreCoeur",forum.getNbreCoeur());
		Forum f = null;
		try{
			f = (Forum) myQuery.getSingleResult();
		}catch(NoResultException e){
			return f;
		}

		f = new Forum();
		f.setNbreCoeur(forum.getNbreCoeur());
		f.setRam(forum.getRam());
		f.setTailleDisk(forum.getTailleDisk());
		

		entityManager.merge(f);
		entityManager.close();

		return f;
    }

    public boolean remove(Forum forum){
    	entityManager = entityManagerFactory.createEntityManager();
    	
    	if(contains(forum))
    	{
    		entityManager = entityManagerFactory.createEntityManager();
        	Query myQuery = entityManager.createQuery("select f from Forum f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
    		myQuery.setParameter("tailleDisk",forum.getTailleDisk());
    		myQuery.setParameter("ram",forum.getRam());
    		myQuery.setParameter("nbreCoeur",forum.getNbreCoeur());
    		Forum f; 
    		
            
    		try{
    			f = (Forum) myQuery.getSingleResult();
    		}catch(NoResultException e){
    			return false;
    		}
    		
    		entityManager.remove(f);
    	}
    		return true;
    		
    	}

    public boolean contains(Forum forum){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select f from Forum f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
		myQuery.setParameter("tailleDisk",forum.getTailleDisk());
		myQuery.setParameter("ram",forum.getRam());
		myQuery.setParameter("nbreCoeur",forum.getNbreCoeur());
		Forum f = null; 
    
		try{
			f = (Forum) myQuery.getSingleResult();
		}catch(NoResultException e){
			return false;
		}
		entityManager.close();
        return true;
    }
    
    @SuppressWarnings("unchecked")
	public List<Forum> getForums(){
    	entityManager = entityManagerFactory.createEntityManager();

		Query query =  entityManager.createQuery("select f from Forum f");
		List<Forum> forumsList = null;
		try{
			forumsList = query.getResultList();
		}catch(NoResultException e){
			forumsList = null;
		}

		return forumsList;
	}


}
