package beans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
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
    @NotNull
    private Calendar beginDate;
    @OneToMany
    private Collection<Offre> offres ;

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

    public Collection<Offre> getOffres() {
        return offres;
    }

    public void setOffres(Collection<Offre> offres) {
        this.offres = offres;
    }
}
