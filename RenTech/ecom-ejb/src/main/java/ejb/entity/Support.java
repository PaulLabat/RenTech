package ejb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Cirie on 10/11/2014.
 */
@Entity
@Table(name = "SUPPORT")
public class Support implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;

    public Support(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
