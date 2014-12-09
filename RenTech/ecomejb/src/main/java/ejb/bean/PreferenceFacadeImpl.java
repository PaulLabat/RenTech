package ejb.bean;



import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ejb.entity.Preference;

import java.util.List;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless(mappedName="PreferenceFacade")
public class PreferenceFacadeImpl implements PreferenceFacadeRemote{
	
	String driver = "jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true";
	
    @PersistenceUnit(unitName="MyFactory")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager ;
    
  
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Preference Calculate() {
    	entityManager = entityManagerFactory.createEntityManager();

		//TODO récupération du top 3 de chaque catégories
    	//TODO calcul des préférences
		Preference u = null;
		try{
			//u = (Preference) query.getSingleResult();
		}catch(NoResultException e){
			return u;
		}
		//Définition des nouvelles préférences
		u = new Preference();

		entityManager.merge(u);
		entityManager.close();

		return u;
    }

    
    @SuppressWarnings("unchecked")
	public List<Preference> getPreferences(){
    	entityManager = entityManagerFactory.createEntityManager();

		Query query =  entityManager.createQuery("select p from Preference p");
		List<Preference> preferencesList = null;
		try{
			preferencesList = query.getResultList();
		}catch(NoResultException e){
			preferencesList = null;
		}

		return preferencesList;
	}


}
