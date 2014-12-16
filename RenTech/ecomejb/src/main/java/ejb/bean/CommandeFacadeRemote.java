package ejb.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import ejb.entity.Commande;

/**
 * Created by augustin on 25/11/14.
 */
@Remote
public interface CommandeFacadeRemote {

    Commande create(Commande commande);

    Commande edit(Commande commande);

    boolean remove(Commande commande);

    boolean contains(Commande commande);
    
    public List<Commande> getCommandes();
    
    public Commande getCommande(String beginDate);
}
