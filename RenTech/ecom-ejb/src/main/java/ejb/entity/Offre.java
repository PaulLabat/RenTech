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
}
