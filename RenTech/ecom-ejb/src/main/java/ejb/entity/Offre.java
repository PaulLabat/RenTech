package ejb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by augustin on 10/11/14.
 */

@Entity
@Table(name="OFFRE")
public class Offre implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal price;

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
