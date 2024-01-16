package com.projet.projet.beans;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "specialite")
public class Specialite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String nom;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "speciality")
	@JsonBackReference
	private Set<Restaurant> restaurants;

	public Specialite() {
		super();
	}

	public Specialite(String nom) {
		super();
		this.nom = nom;
	}

	public Specialite(@NotEmpty String nom, Set<Restaurant> restaurants) {
		this.nom = nom;
		this.restaurants = restaurants;
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

	@Override
	public String toString() {
		return "Specialite [id=" + id + ", nom=" + nom + "]";
	}

	public Set<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Set<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}



}
