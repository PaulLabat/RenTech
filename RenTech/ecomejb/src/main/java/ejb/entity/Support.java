package ejb.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Cirie on 10/11/2014.
 */
@Entity
@Table(name = "SUPPORT")
public class Support implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    @OneToMany
    private Collection<Git> gits;
    @OneToMany
    private Collection<SiteWeb> siteWebs;
    @OneToMany
    private Collection<Forum> forums;

    public Support(){
        gits = new ArrayList<Git>();
        siteWebs = new ArrayList<SiteWeb>();
        forums = new ArrayList<Forum>();
    }

    public Integer getId() {
        return id;
    }

    public Collection<Git> getGits() {
        return gits;
    }

    public void setGits(Collection<Git> gits) {
        this.gits = gits;
    }

    public Collection<SiteWeb> getSiteWebs() {
        return siteWebs;
    }

    public void setSiteWebs(Collection<SiteWeb> siteWebs) {
        this.siteWebs = siteWebs;
    }

    public Collection<Forum> getForums() {
        return forums;
    }

    public void setForums(Collection<Forum> forums) {
        this.forums = forums;
    }
}
