package ejb.bean;

import ejb.entity.Utilisateur;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.io.Serializable;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless
public class UtilisateurFacadeImpl implements UtilisateurFacadeRemote, Serializable{
    @PersistenceUnit(unitName="MyFactory")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    public void create(Utilisateur utilisateur) {
        entityManager.persist(utilisateur);
    }

    public void edit(Utilisateur utilisateur) {
        entityManager.merge(utilisateur);
    }

    public void remove(Utilisateur utilisateur) {
        entityManager.remove(utilisateur);
    }

    public boolean contains(Utilisateur utilisateur){
        return entityManager.contains(utilisateur);
    }

	public Utilisateur find(Utilisateur utilisateur) {
		return (Utilisateur) entityManager.createQuery("select * from Personne where mail='"+utilisateur.getMail()+"';").getResultList().get(0);
	}
}
