package ejb.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import ejb.entity.Preference;

/**
 * Created by augustin on 25/11/14.
 */
@Remote
public interface PreferenceFacadeRemote {

    Preference Calculate();
    
    public List<Preference> getPreferences();
}
