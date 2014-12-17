package ejb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Cirie on 10/11/2014.
 */
@Entity
@Table(name = "SERVEURVIRTUEL")
public class ServeurVirtuel implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @NotNull
    private Integer tailleDisk;
    @NotNull
    private Integer ram;
    @NotNull
    private Integer nbreCoeur;
    @OneToMany
    private Collection<Git> gits;
    @OneToMany
    private Collection<SiteWeb> siteWebs;
    @OneToMany
    private Collection<Forum> forums;

    public ServeurVirtuel(){

    }

    public Integer getId() {
        return id;
    }

    public Integer getTailleDisk() {
        return tailleDisk;
    }

    public void setTailleDisk(Integer tailleDisk) {
        this.tailleDisk = tailleDisk;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getNbreCoeur() {
        return nbreCoeur;
    }

    public void setNbreCoeur(Integer nbreCoeur) {
        this.nbreCoeur = nbreCoeur;
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
