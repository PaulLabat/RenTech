package ejb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

/**
 * Created by Cirie on 10/11/2014.
 */
@Entity
@Table(name = "UTILISATEUR")
public class Utilisateur implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @NotNull
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message="{invalid.email}")
    private String mail;
    @NotNull
    private String mdp;
    @NotNull
    private String nom;
    private String prenom;
    @NotNull
    private Boolean validate;
    @NotNull
    private String hashCodeToValidate;
    private String adresseFactu;
    @ManyToOne
    private Entreprise entreprise;
    @ManyToOne
    private Association association;
    @OneToMany
    private Collection<Commande> commandes ;
    @OneToMany
    private Collection<ServeurPhysique> serveurPhysiques;
    @OneToMany
    private Collection<Git> gits;
    @OneToMany
    private Collection<SiteWeb> siteWebs;
    @OneToMany
    private Collection<Forum> forums;

    public Utilisateur(){
        this.validate = false;
    }

    public Integer getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    /**
     *
     * @param mdp : le mdp doit être chiffré avant d'être passé en paramètre dans cette méthode
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Boolean getValidate() {
        return validate;
    }

    public void setValidate(Boolean validate) {
        this.validate = validate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHashCodeToValidate() {
        return hashCodeToValidate;
    }

    public void setHashCodeToValidate(String hashCodeToValidate) {
        this.hashCodeToValidate = hashCodeToValidate;
    }

    public String getAdresseFactu() {
        return adresseFactu;
    }

    public void setAdresseFactu(String adresseFactu) {
        this.adresseFactu = adresseFactu;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

    public Collection<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Collection<Commande> commandes) {
        this.commandes = commandes;
    }

    public Collection<ServeurPhysique> getServeurPhysiques() {
        return serveurPhysiques;
    }

    public void setServeurPhysiques(Collection<ServeurPhysique> serveurPhysiques) {
        this.serveurPhysiques = serveurPhysiques;
    }

    public Collection<Git> getGits() {
        return gits;
    }

    public void setGits(Collection<Git> gits) {
        this.gits = gits;
    }

    public Collection<SiteWeb> getSiteWebs() {
        return siteWebs;
    }

    public void setSiteWebs(Collection<SiteWeb> siteWebs) {
        this.siteWebs = siteWebs;
    }

    public Collection<Forum> getForums() {
        return forums;
    }

    public void setForums(Collection<Forum> forums) {
        this.forums = forums;
    }

    @Override
    public String toString() {
        return "id : "+this.id + " name : "+this.nom + " email : "+ this.mail + " mdp : "+this.mdp + " valide : "+this.validate;
    }
}
