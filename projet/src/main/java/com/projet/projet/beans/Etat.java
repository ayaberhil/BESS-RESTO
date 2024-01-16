package com.projet.projet.beans;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="etat")
public class Etat {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@NotEmpty
	private String nom; //  EN_COURS, VALIDE, NON_VALIDE; 

	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="etat")
	@JsonBackReference
	private Set<Restaurant> restaurant;

	public Etat() {
		super();
	}

	public Etat(@NotEmpty String nom, Set<Restaurant> restaurant) {
		super();
		this.nom = nom;
		this.restaurant = restaurant;
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

	public Set<Restaurant> getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Set<Restaurant> restaurant) {
		this.restaurant = restaurant;
	}	
	
}
