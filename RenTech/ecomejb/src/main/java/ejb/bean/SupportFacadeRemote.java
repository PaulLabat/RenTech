package ejb.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import ejb.entity.Support;

/**
 * Created by augustin on 25/11/14.
 */
@Remote
public interface SupportFacadeRemote {

    Support create(Support support);

    Support edit(Support support);

    boolean remove(Support support);

    boolean contains(Support support);
    
    public List<Support> getSupports();
}
