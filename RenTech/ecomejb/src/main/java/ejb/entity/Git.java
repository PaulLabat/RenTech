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
@Table(name = "GIT")
public class Git implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private Integer tailleDisk;
    @NotNull
    private Integer ram;
    @NotNull
    private Integer nbreCoeur;

    public Git(){

    }

    public Integer getId() {
        return id;
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

	@Override
	public String toString() {
		return "\nGit [id = "+this.id+ " tailleDisk=" + tailleDisk + ", ram=" + ram
				+ ", nbreCoeur=" + nbreCoeur + "]";
	}
    

}
