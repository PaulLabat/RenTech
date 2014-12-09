package ejb.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import ejb.entity.ServeurVirtuel;

/**
 * Created by augustin on 25/11/14.
 */
@Remote
public interface ServeurVirtuelFacadeRemote {

    ServeurVirtuel create(ServeurVirtuel serveurVirtuel);

    ServeurVirtuel edit(ServeurVirtuel serveurVirtuel);

    boolean remove(ServeurVirtuel serveurVirtuel);

    boolean contains(ServeurVirtuel serveurVirtuel);
    
    public List<ServeurVirtuel> getServeurVirtuels();
}
