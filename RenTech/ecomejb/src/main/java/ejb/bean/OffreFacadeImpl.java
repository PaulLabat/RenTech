package ejb.bean;



import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ejb.entity.Offre;

import java.util.List;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless(mappedName="OffreFacade")
public class OffreFacadeImpl implements OffreFacadeRemote{
	
	String driver = "jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true";
	
    @PersistenceUnit(unitName="MyFactory")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager ;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Offre create(Offre offre){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select o from Offre o where o.price = :price");
		myQuery.setParameter("price",offre.getPrice());
		Offre o=null; 
		try{
			myQuery.getSingleResult();
		}catch(NoResultException e){
			o = new Offre();
			o.setPrice(offre.getPrice());
			o.setGits(offre.getGits());
			o.setForums(offre.getForums());
			o.setServeurPhysiques(offre.getServeurPhysiques());
			o.setServeurVirtuels(offre.getServeurVirtuels());
			o.setSiteWebs(offre.getSiteWebs());
			entityManager.persist(o);
		}
		entityManager.close();
		return o;
	}
  
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Offre edit(Offre offre) {
    	entityManager = entityManagerFactory.createEntityManager();

    	Query myQuery = entityManager.createQuery("select o from Offre o where o.price = :price");
		myQuery.setParameter("price",offre.getPrice());
		Offre o=null; 
		try{
			o = (Offre) myQuery.getSingleResult();
		}catch(NoResultException e){
			return o;
		}

		o = new Offre();
		o.setPrice(offre.getPrice());
		o.setGits(offre.getGits());
		o.setForums(offre.getForums());
		o.setServeurPhysiques(offre.getServeurPhysiques());
		o.setServeurVirtuels(offre.getServeurVirtuels());
		o.setSiteWebs(offre.getSiteWebs());

		entityManager.merge(o);
		entityManager.close();

		return o;
    }

    public boolean remove(Offre offre){
    	entityManager = entityManagerFactory.createEntityManager();
    	
    	if(contains(offre))
    	{
    		entityManager = entityManagerFactory.createEntityManager();
        	Query myQuery = entityManager.createQuery("select o from Offre o where o.price = :price");
    		myQuery.setParameter("price",offre.getPrice());
    		Offre o; 
    		
            
    		try{
    			o = (Offre) myQuery.getSingleResult();
    		}catch(NoResultException e){
    			return false;
    		}
    		
    		entityManager.remove(o);
    	}
    		return true;
    		
    	}

    public boolean contains(Offre offre){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select o from Offre o where o.price = :price");
		myQuery.setParameter("price",offre.getPrice());
		Offre o; 
    
		try{
			o = (Offre) myQuery.getSingleResult();
			o.toString();
		}catch(NoResultException e){
			return false;
		}
		entityManager.close();
        return true;
    }
    
    @SuppressWarnings("unchecked")
	public List<Offre> getOffres(){
    	entityManager = entityManagerFactory.createEntityManager();

		Query query =  entityManager.createQuery("select c from Offre c");
		List<Offre> offreList = null;
		try{
			offreList = query.getResultList();
		}catch(NoResultException e){
			offreList = null;
		}

		return offreList;
	}
    
	public Offre getOffre(String beginDate){
    	entityManager = entityManagerFactory.createEntityManager();

    	 Query query = entityManager.createQuery("select c from Offre c where c.beginDate = :beginDate");
    	query.setParameter("beginDate", beginDate);
		Offre offre = null;
		try{
			offre = (Offre) query.getSingleResult();
		}catch(NoResultException e){
			offre = null;
		}

		return offre;
	}


}
