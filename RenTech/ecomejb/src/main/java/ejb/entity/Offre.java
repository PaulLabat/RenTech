package ejb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by augustin on 10/11/14.
 */

@Entity
@Table(name="OFFRE")
public class Offre implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private BigDecimal price;
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

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

	public Collection<ServeurVirtuel> getServeurVirtuels() {
		return serveurVirtuels;
	}

	public void setServeurVirtuels(Collection<ServeurVirtuel> serveurVirtuels) {
		this.serveurVirtuels = serveurVirtuels;
	}
    
    
}
