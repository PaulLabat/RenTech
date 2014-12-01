package ejb.bean;

import ejb.entity.Utilisateur;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import java.io.Serializable;

/**
 * Created by augustin on 25/11/14.
 */

@DataSourceDefinition(name="derby",
        minPoolSize = 0,
        initialPoolSize = 0,
        className = "org.apache.derby.jdbc.ClientDataSource",
        portNumber = 1527,
        serverName = "localhost",
        user = "ecom",
        password = "ecom",
        databaseName = "ecomBdd",
        properties = {"connectionAttibutes=;create=true"}
)

@Stateless
public class UtilisateurFacadeImpl implements UtilisateurFacadeRemote, Serializable{
    @PersistenceUnit(unitName="MyFactory")
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
