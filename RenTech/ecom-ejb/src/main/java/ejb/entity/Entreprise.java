package ejb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Cirie on 10/11/2014.
 */
@Entity
@Table(name = "ENTREPRISE")
public class Entreprise implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private String nom;

    public Entreprise(){

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
