package ejb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Cirie on 10/11/2014.
 */
@Entity
@Table(name = "COMMANDE")
public class Commande implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    private Calendar beginDate;

    public Commande(){
        beginDate = Calendar.getInstance();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate(){
        return this.beginDate.getTime();
    }
}
