package ejb.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import ejb.entity.ServeurPhysique;

/**
 * Created by augustin on 25/11/14.
 */
@Remote
public interface ServeurPhysiqueFacadeRemote {

    ServeurPhysique create(ServeurPhysique serveurPhysique);

    ServeurPhysique edit(ServeurPhysique serveurPhysique);

    boolean remove(ServeurPhysique serveurPhysique);

    boolean contains(ServeurPhysique serveurPhysique);
    
    public List<ServeurPhysique> getServeurPhysiques();
}
