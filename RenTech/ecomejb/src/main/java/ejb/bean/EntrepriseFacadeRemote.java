package ejb.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import ejb.entity.Entreprise;

/**
 * Created by augustin on 25/11/14.
 */
@Remote
public interface EntrepriseFacadeRemote {

    Entreprise create(Entreprise entreprise);

    Entreprise edit(Entreprise entreprise);

    boolean remove(Entreprise entreprise);

    boolean contains(Entreprise entreprise);
    
    public List<Entreprise> getEntreprises();
}
