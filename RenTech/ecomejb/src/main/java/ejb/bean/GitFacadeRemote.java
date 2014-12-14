package ejb.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import ejb.entity.Git;

/**
 * Created by augustin on 25/11/14.
 */
@Remote
public interface GitFacadeRemote {

    Git create(Git git);

    Git edit(Git git);

    boolean remove(Git git);

    boolean contains(Git git);
    
    public List<Git> getGits();
}
