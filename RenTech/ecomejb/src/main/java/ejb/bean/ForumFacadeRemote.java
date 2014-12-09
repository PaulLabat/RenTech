package ejb.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import ejb.entity.Forum;

/**
 * Created by augustin on 25/11/14.
 */
@Remote
public interface ForumFacadeRemote {

    Forum create(Forum forum);

    Forum edit(Forum forum);

    boolean remove(Forum forum);

    boolean contains(Forum forum);
    
    public List<Forum> getForums();
}
