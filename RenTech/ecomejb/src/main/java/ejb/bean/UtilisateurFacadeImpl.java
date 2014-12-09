package ejb.bean;

import ejb.entity.Utilisateur;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by augustin on 25/11/14.
 */

@Stateless(mappedName="UtilisateurFacade")
public class UtilisateurFacadeImpl implements UtilisateurFacadeRemote{
	
	String driver = "jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true";
	
    @PersistenceUnit(unitName="MyFactory")
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager ;
    
    public void create(Utilisateur utilisateur){
    	entityManager = entityManagerFactory.createEntityManager();
    }

    public void edit(Utilisateur utilisateur) {
    	entityManager = entityManagerFactory.createEntityManager();
    }

    public void remove(Utilisateur utilisateur) {
    	entityManager = entityManagerFactory.createEntityManager();
    }

    public boolean contains(Utilisateur utilisateur) throws SQLException{
    	entityManager = entityManagerFactory.createEntityManager();
    	return true;
    }

	public boolean find(Utilisateur utilisateur) throws SQLException {
		entityManager = entityManagerFactory.createEntityManager();
		Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true");
        
        PreparedStatement statement = con.prepareStatement("select * from UTILISATEUR");
        ResultSet rs2 = statement.executeQuery();
        
        return (rs2.next());
		
	}
	
	public void insertDB(Utilisateur utilisateur) throws SQLException {
		Connection con = DriverManager.getConnection(driver);
        
        Statement statement = con.createStatement();
        statement.executeUpdate("insert into UTILISATEUR (ID,MAIL) values (0,'"+utilisateur.getMail()+"')");        
        
	}
}
