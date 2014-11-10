package ejb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Cirie on 10/11/2014.
 */
@Entity
@Table(name = "ASSOCIATION")
public class Association {

    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private String nom;

    public Association(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
