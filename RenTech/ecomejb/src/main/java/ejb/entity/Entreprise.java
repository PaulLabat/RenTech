package ejb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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
    @OneToMany(mappedBy = "entreprise")
    private Collection<Utilisateur> utilisateurs ;

    public Entreprise(){
        utilisateurs = new ArrayList<Utilisateur>();
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Collection<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Collection<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
}
