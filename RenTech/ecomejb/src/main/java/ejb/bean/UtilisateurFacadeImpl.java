package ejb.bean;

import ejb.entity.Utilisateur;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless
public class UtilisateurFacadeImpl implements UtilisateurFacadeRemote, Serializable{
    @PersistenceContext(unitName = "myManager")
    private EntityManager entityManager;

    @Override
    public void create(Utilisateur utilisateur) {
        entityManager.persist(utilisateur);
    }

    @Override
    public void edit(Utilisateur utilisateur) {
        entityManager.merge(utilisateur);
    }

    @Override
    public void remove(Utilisateur utilisateur) {
        entityManager.remove(utilisateur);
    }

    @Override
    public boolean contains(Utilisateur utilisateur){
        return entityManager.contains(utilisateur);
    }
}
