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
@Table(name = "SERVEURPHYSIQUE")
public class ServeurPhysique implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @NotNull
    private Integer tailleDisk;
    @NotNull
    private Integer ram;
    @NotNull
    private Integer nbreCoeur;
    //supprimer un serveur physique entrainera la suppression des serveurs virtuels
    @OneToMany
    private Collection<ServeurVirtuel> serveurVirtuels;

    public ServeurPhysique(){
        serveurVirtuels = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTailleDisk() {
        return tailleDisk;
    }

    public void setTailleDisk(Integer tailleDisk) {
        this.tailleDisk = tailleDisk;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getNbreCoeur() {
        return nbreCoeur;
    }

    public void setNbreCoeur(Integer nbreCoeur) {
        this.nbreCoeur = nbreCoeur;
    }

    public Collection<ServeurVirtuel> getServeurVirtuels() {
        return serveurVirtuels;
    }

    public void setServeurVirtuels(Collection<ServeurVirtuel> serveurVirtuels) {
        this.serveurVirtuels = serveurVirtuels;
    }
}
