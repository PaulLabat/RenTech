package ejb.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import ejb.entity.SiteWeb;

/**
 * Created by augustin on 25/11/14.
 */
@Remote
public interface SiteWebFacadeRemote {

    SiteWeb create(SiteWeb siteweb);

    SiteWeb edit(SiteWeb siteweb);

    boolean remove(SiteWeb siteweb);

    boolean contains(SiteWeb siteweb);
    
    public List<SiteWeb> getSiteWebs();
}
