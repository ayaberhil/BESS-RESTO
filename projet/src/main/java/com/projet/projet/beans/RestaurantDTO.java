package com.projet.projet.beans;

import java.io.Serializable;
import java.util.Set;

public class RestaurantDTO implements Serializable {
	
	private Integer id;
	private String nom;
	private String adresse;
	private Double lati;
	private Double longi;
	private Integer rank;
	private String hourop;
	private String hourcl;
	private Serie serie;
	private Zone zone;
    private Set<Specialite> specialities;
	private Etat etat;
	private Set<Photo> photos;
	private User proprietaire;

    public RestaurantDTO() {
        super();
    }

    public RestaurantDTO(String nom, String adresse, Double lati, Double longi, Integer rank, String hourop,
            String hourcl, Serie serie, Zone zone, Set<Specialite> specialities, Etat etat,
            Set<Photo> photos, User proprietaire) {
                super();
        this.nom = nom;
        this.adresse = adresse;
        this.lati = lati;
        this.longi = longi;
        this.rank = rank;
        this.hourop = hourop;
        this.hourcl = hourcl;
        this.serie = serie;
        this.zone = zone;
        this.specialities = specialities;
        this.etat = etat;
        this.photos = photos;
        this.proprietaire = proprietaire;
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

    public Set<Specialite> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Specialite> specialities) {
        this.specialities = specialities;
    } 

}