package ejb.bean;



import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ejb.entity.SiteWeb;

import java.util.List;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless(mappedName="SiteWebFacade")
public class SiteWebFacadeImpl implements SiteWebFacadeRemote{
	
	String driver = "jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true";
	
    @PersistenceUnit(unitName="MyFactory")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager ;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SiteWeb create(SiteWeb siteweb){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select f from SiteWeb f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
		myQuery.setParameter("tailleDisk",siteweb.getTailleDisk());
		myQuery.setParameter("ram",siteweb.getRam());
		myQuery.setParameter("nbreCoeur",siteweb.getNbreCoeur());
    	siteweb.toString();
		SiteWeb f = null;
		try{
			myQuery.getSingleResult();
		}catch(NoResultException e){
			f = new SiteWeb();
			f.setNbreCoeur(siteweb.getNbreCoeur());
			f.setRam(siteweb.getRam());
			f.setTailleDisk(siteweb.getTailleDisk());
			entityManager.persist(f);
		}
		entityManager.close();
		return f;
	}
  
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SiteWeb edit(SiteWeb siteweb) {
    	entityManager = entityManagerFactory.createEntityManager();

    	Query myQuery = entityManager.createQuery("select f from SiteWeb f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
		myQuery.setParameter("tailleDisk",siteweb.getTailleDisk());
		myQuery.setParameter("ram",siteweb.getRam());
		myQuery.setParameter("nbreCoeur",siteweb.getNbreCoeur());
		SiteWeb f = null;
		try{
			f = (SiteWeb) myQuery.getSingleResult();
		}catch(NoResultException e){
			return f;
		}

		f = new SiteWeb();
		f.setNbreCoeur(siteweb.getNbreCoeur());
		f.setRam(siteweb.getRam());
		f.setTailleDisk(siteweb.getTailleDisk());
		

		entityManager.merge(f);
		entityManager.close();

		return f;
    }

    public boolean remove(SiteWeb siteweb){
    	entityManager = entityManagerFactory.createEntityManager();
    	
    	if(contains(siteweb))
    	{
    		entityManager = entityManagerFactory.createEntityManager();
        	Query myQuery = entityManager.createQuery("select f from SiteWeb f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
    		myQuery.setParameter("tailleDisk",siteweb.getTailleDisk());
    		myQuery.setParameter("ram",siteweb.getRam());
    		myQuery.setParameter("nbreCoeur",siteweb.getNbreCoeur());
    		SiteWeb f; 
    		
            
    		try{
    			f = (SiteWeb) myQuery.getSingleResult();
    		}catch(NoResultException e){
    			return false;
    		}
    		
    		entityManager.remove(f);
    	}
    		return true;
    		
    	}

    public boolean contains(SiteWeb siteweb){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select f from SiteWeb f where f.tailleDisk = :tailleDisk AND f.ram = :ram AND f.nbreCoeur = :nbreCoeur");
		myQuery.setParameter("tailleDisk",siteweb.getTailleDisk());
		myQuery.setParameter("ram",siteweb.getRam());
		myQuery.setParameter("nbreCoeur",siteweb.getNbreCoeur());
		SiteWeb f = null; 
    
		try{
			f = (SiteWeb) myQuery.getSingleResult();
		}catch(NoResultException e){
			return false;
		}
		entityManager.close();
        return true;
    }
    
    @SuppressWarnings("unchecked")
	public List<SiteWeb> getSiteWebs(){
    	entityManager = entityManagerFactory.createEntityManager();

		Query query =  entityManager.createQuery("select f from SiteWeb f");
		List<SiteWeb> sitewebsList = null;
		try{
			sitewebsList = query.getResultList();
		}catch(NoResultException e){
			sitewebsList = null;
		}

		return sitewebsList;
	}


}
