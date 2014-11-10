package ejb.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by augustin on 10/11/14.
 */

@Entity
@Table(name="OFFRE")
public class Offre implements Serializable{
    @Id
    private Integer id;
    private Integer price;

    public Offre(){

    }

    public Offre(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
