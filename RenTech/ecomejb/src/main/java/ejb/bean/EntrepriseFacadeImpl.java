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

import java.util.List;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless(mappedName="EntrepriseFacade")
public class EntrepriseFacadeImpl implements EntrepriseFacadeRemote{
	
	String driver = "jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true";
	
    @PersistenceUnit(unitName="MyFactory")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager ;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Entreprise create(Entreprise entreprise){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery =  entityManager.createQuery("select e from Entreprise e where e.nom = :nom");
    	myQuery.setParameter("nom", entreprise.getNom());
		Entreprise e = null;
		try{
			myQuery.getSingleResult();
		}catch(NoResultException ex){
			e = new Entreprise();
			e.setNom(entreprise.getNom());
			entityManager.persist(e);
		}
		entityManager.close();
		return e;
	}
  
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Entreprise edit(Entreprise entreprise) {
    	entityManager = entityManagerFactory.createEntityManager();

		Query query =  entityManager.createQuery("select e from Entreprise e where e.nom = :nom");
		query.setParameter("nom",entreprise.getNom());
		Entreprise e = null;
		try{
			e = (Entreprise) query.getSingleResult();
		}catch(NoResultException ex){
			return e;
		}

		e = new Entreprise();
		e.setNom(entreprise.getNom());

		entityManager.merge(e);
		entityManager.close();

		return e;
    }

    public boolean remove(Entreprise entreprise){
    	entityManager = entityManagerFactory.createEntityManager();
    	
    	if(contains(entreprise))
    	{
    		entityManager = entityManagerFactory.createEntityManager();
        	Query myQuery = entityManager.createQuery("select e from Entreprise e where e.nom = :nom");
        	myQuery.setParameter("nom",entreprise.getNom());
    		Entreprise u; 
    		
            
    		try{
    			u = (Entreprise) myQuery.getSingleResult();
    		}catch(NoResultException e){
    			return false;
    		}
    		
    		entityManager.remove(u);
    	}
    		return true;
    		
    	}

    public boolean contains(Entreprise entreprise){
    	entityManager = entityManagerFactory.createEntityManager();
    	Query myQuery = entityManager.createQuery("select e from Entreprise e where e.nom = :nom");
		myQuery.setParameter("nom",entreprise.getNom());
		Entreprise u; 
    
		try{
			u = (Entreprise) myQuery.getSingleResult();
			u.toString();
		}catch(NoResultException e){
			return false;
		}
		entityManager.close();
        return true;
    }
    
    @SuppressWarnings("unchecked")
	public List<Entreprise> getEntreprises(){
    	entityManager = entityManagerFactory.createEntityManager();

		Query query =  entityManager.createQuery("select e from Entreprise e");
		List<Entreprise> entrepriseList = null;
		try{
			entrepriseList = query.getResultList();
		}catch(NoResultException e){
			entrepriseList = null;
		}

		return entrepriseList;
	}


}
