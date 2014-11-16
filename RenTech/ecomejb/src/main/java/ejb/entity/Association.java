package ejb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Cirie on 10/11/2014.
 */
@Entity
@Table(name = "ASSOCIATION")
public class Association implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private String nom;
    @OneToMany(mappedBy="utilisateur")
    private Collection<Utilisateur> utilisateurs ;

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
