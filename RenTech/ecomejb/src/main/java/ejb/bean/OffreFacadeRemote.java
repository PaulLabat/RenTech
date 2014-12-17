package ejb.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import ejb.entity.Offre;

/**
 * Created by augustin on 25/11/14.
 */
@Remote
public interface OffreFacadeRemote {

    Offre create(Offre offre);

    Offre edit(Offre offre);

    boolean remove(Offre offre);

    boolean contains(Offre offre);
    
    public List<Offre> getOffres();
    
    public Offre getOffre(String beginDate);
}
