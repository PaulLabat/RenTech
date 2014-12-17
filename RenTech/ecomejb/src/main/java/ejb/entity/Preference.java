package ejb.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by augustin on 25/11/14.
 */

@Entity
@Table(name ="PREFERENCE")
public class Preference implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToMany
    private Collection<Forum> forums;
    @ManyToMany
    private Collection<Git> gits;
    @ManyToMany
    private Collection<SiteWeb> siteWebs;
    @ManyToMany
    private Collection<Association> associations;
    @ManyToMany
    private Collection<Entreprise> entreprises;

    public Preference(){
        forums = new ArrayList<Forum>();
        gits = new ArrayList<Git>();
        siteWebs = new ArrayList<SiteWeb>();
        associations = new ArrayList<Association>();
        entreprises = new ArrayList<Entreprise>();
    }

    public Integer getId() {
        return id;
    }

    public Collection<Forum> getForums() {
        return forums;
    }

    public void setForums(Collection<Forum> forums) {
        this.forums = forums;
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

    public Collection<Association> getAssociations() {
        return associations;
    }

    public void setAssociations(Collection<Association> associations) {
        this.associations = associations;
    }

    public Collection<Entreprise> getEntreprises() {
        return entreprises;
    }

    public void setEntreprises(Collection<Entreprise> entreprises) {
        this.entreprises = entreprises;
    }
}
