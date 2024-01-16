package com.projet.projet.beans;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @NotEmpty
    private String adresse;
    private Double lati;
    private Double longi;

    @Column(name = "score")
    private Integer rank;
    @NotEmpty
    private String hourop;
    @NotEmpty
    private String hourcl;

    @ManyToOne
    @JoinColumn(name = "serie_id", nullable = false)
    @JsonBackReference
    private Serie serie;

    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    @JsonBackReference
    private Zone zone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "speciality_id", nullable = false)
    @JsonManagedReference
    private Specialite speciality;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "etat_id", nullable = false)
    @JsonManagedReference
    private Etat etat;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "restaurant")
    @JsonBackReference
    private Set<Photo> photos;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proprietaire_id", referencedColumnName = "id")
    @JsonManagedReference
    private User proprietaire;

    public Restaurant() {
        super();
    }

    public Restaurant(String nom, @NotEmpty String adresse, Double lati, Double longi, Integer rank,
            @NotEmpty String hourop, @NotEmpty String hourcl, Serie serie, Zone zone,
            Specialite speciality, Etat etat, Set<Photo> photos, User proprietaire) {
        this.nom = nom;
        this.adresse = adresse;
        this.lati = lati;
        this.longi = longi;
        this.rank = rank;
        this.hourop = hourop;
        this.hourcl = hourcl;
        this.serie = serie;
        this.zone = zone;
        this.speciality = speciality;
        this.etat = etat;
        this.photos = photos;
        this.proprietaire = proprietaire;
    }

    public Restaurant(String nom, @NotEmpty String adresse, Double lati, Double longi, Integer rank,
            @NotEmpty String hourop, @NotEmpty String hourcl, Serie serie, Zone zone,
            Specialite speciality, Etat etat, User proprietaire) {
        this.nom = nom;
        this.adresse = adresse;
        this.lati = lati;
        this.longi = longi;
        this.rank = rank;
        this.hourop = hourop;
        this.hourcl = hourcl;
        this.serie = serie;
        this.zone = zone;
        this.speciality = speciality;
        this.etat = etat;
        this.proprietaire = proprietaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Double getLati() {
        return lati;
    }

    public void setLati(Double lati) {
        this.lati = lati;
    }

    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getHourop() {
        return hourop;
    }

    public void setHourop(String hourop) {
        this.hourop = hourop;
    }

    public String getHourcl() {
        return hourcl;
    }

    public void setHourcl(String hourcl) {
        this.hourcl = hourcl;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public User getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(User proprietaire) {
        this.proprietaire = proprietaire;
    }

    public Specialite getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Specialite speciality) {
        this.speciality = speciality;
    }

}
