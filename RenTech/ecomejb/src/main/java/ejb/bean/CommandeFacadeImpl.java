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
import ejb.entity.Offre;

import java.util.Collection;
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
    	Query myQuery =  entityManager.createQuery("select c from Commande c where c.adresseFactu = :adresseFactu AND c.beginDate = :beginDate");
    	myQuery.setParameter("adresseFactu", commande.getAdresseFactu());
		myQuery.setParameter("beginDate",commande.getDate());
		Commande c = null;
		try{
			myQuery.getSingleResult();
		}catch(NoResultException e){
			c = new Commande();
			c.setOffres(commande.getOffres());
			c.setAdresseFactu(commande.getAdresseFactu());
			entityManager.persist(c);
		}
		entityManager.close();
		return c;
	}
  
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Commande edit(Commande commande) {
    	entityManager = entityManagerFactory.createEntityManager();

		Query myQuery =  entityManager.createQuery("select c from Commande c where c.adresseFactu = :adresseFactu AND c.beginDate = :beginDate");
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
		List<Commande> commandeList = null;
		try{
			commandeList = query.getResultList();
		}catch(NoResultException e){
			commandeList = null;
		}

		return commandeList;
	}
    
	public Commande getCommande(String beginDate){
    	entityManager = entityManagerFactory.createEntityManager();

    	 Query query = entityManager.createQuery("select c from Commande c where c.beginDate = :beginDate");
    	query.setParameter("beginDate", beginDate);
		Commande commande = null;
		try{
			commande = (Commande) query.getSingleResult();
		}catch(NoResultException e){
			commande = null;
		}

		return commande;
	}

	public String printTable(){
		entityManager = entityManagerFactory.createEntityManager();

		Query myQuery = entityManager.createQuery("select u from Commande u");

		List<Commande> list = myQuery.getResultList();
		String result ="nb of user : "+list.size() +"\n";
		for(Commande u : list){
			result = result + u.toString() +"\n";
		}

		entityManager.close();
		return result;
	}


}
