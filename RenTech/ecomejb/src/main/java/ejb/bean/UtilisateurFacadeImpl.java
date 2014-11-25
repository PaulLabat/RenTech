package ejb.bean;

import ejb.entity.Utilisateur;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless
public class UtilisateurFacadeImpl implements UtilisateurFacadeRemote, UtilisateurFacadeLocal{
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
        entityManager.merge(utilisateur);
    }
}
