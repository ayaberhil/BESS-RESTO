package com.projet.projet.beans;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotEmpty
    private String mail;
	@NotEmpty
    private String nom;
	@NotEmpty
    private String prenom;
	@NotEmpty
    private String password;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( 
        name = "users_roles", 
        joinColumns = @JoinColumn(
        name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
        name = "role_id", referencedColumnName = "id"))
	@JsonBackReference
	private Set<Role> roles;

	@OneToMany(mappedBy = "proprietaire")
	@JsonBackReference
	private Set<Restaurant> restaurants;

    public User() {
		super();
	}
	
	public User(@NotEmpty String mail, @NotEmpty String nom, @NotEmpty String prenom, @NotEmpty String password,
			Set<Role> roles) {
		super();
		this.mail = mail;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.roles = roles;
	}
	
	public Long getUserId() {
		return id;
	}
	public void setUserId(Long userId) {
		this.id = userId;
	}

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Set<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
   
	
}