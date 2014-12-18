package ejb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by augustin on 10/11/14.
 */

@Entity
@Table(name="OFFRE")
public class Offre implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @NotNull
    private Double price;
    @OneToMany
    private Collection<ServeurPhysique> serveurPhysiques;
    @OneToMany
    private Collection<ServeurVirtuel> serveurVirtuels;
    @OneToMany
    private Collection<Git> gits;
    @OneToMany
    private Collection<SiteWeb> siteWebs;
    @OneToMany
    private Collection<Forum> forums;

    public Offre(){
		serveurPhysiques = new ArrayList<ServeurPhysique>();
		serveurVirtuels = new ArrayList<ServeurVirtuel>();
		gits = new ArrayList<Git>();
		siteWebs = new ArrayList<SiteWeb>();
		forums = new ArrayList<Forum>();
    }

    public Integer getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

	public Collection<ServeurPhysique> getServeurPhysiques() {
		return serveurPhysiques;
	}

	public void setServeurPhysiques(Collection<ServeurPhysique> serveurPhysiques) {
		this.serveurPhysiques = serveurPhysiques;
	}

	public Collection<Git> getGits() {
		return gits;
	}

	public void setGits(Collection<Git> gits) {
		this.gits = gits;
	}

	public void addGit(Git git){
		this.gits.add(git);
	}

	public Collection<SiteWeb> getSiteWebs() {
		return siteWebs;
	}

	public void setSiteWebs(Collection<SiteWeb> siteWebs) {
		this.siteWebs = siteWebs;
	}

	public void addSiteWebs(SiteWeb siteWeb){
		this.siteWebs.add(siteWeb);
	}

	public Collection<Forum> getForums() {
		return forums;
	}

	public void setForums(Collection<Forum> forums) {
		this.forums = forums;
	}

	public Collection<ServeurVirtuel> getServeurVirtuels() {
		return serveurVirtuels;
	}

	public void setServeurVirtuels(Collection<ServeurVirtuel> serveurVirtuels) {
		this.serveurVirtuels = serveurVirtuels;
	}

	@Override
	public String toString(){
		return "\n id : "+this.id + " Gits : "+toStringGit();
	}

	private String toStringGit(){
		String result = "";
		for(Git g : gits){
			result = result + "\n"+g.toString();
		}
		return  result;
	}
    
}
