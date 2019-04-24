package ch.hearc.spring.echoppe.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class Utilisateur {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2)
	private String nomUtilisateur;
	
	@Email
	private String email;
	private String motDePasse;
	
	@ManyToMany
	private Set<Role> roles;

	// Getters
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return nomUtilisateur;
	}

	public String getEmail() {
		return email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	// Setters
	public void setUsername(String username) {
		this.nomUtilisateur = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	// Methods
	public void addRole(Role role) {
		this.roles.add(role);
		
	}
	public void addRoles(Set<Role> roles) {
		this.roles.addAll(roles);
	}

	// Constructor
	public Utilisateur(String nomUtilisateur, String email, String motDePasse) {
		this.nomUtilisateur = nomUtilisateur;
		this.email = email;
		this.motDePasse = motDePasse;
		this.roles=new HashSet<Role>();
	}

	public Utilisateur() {
	}

	// ToString, Hashcode, Equals
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + nomUtilisateur + ", email=" + email + ", motDePasse=" + motDePasse
				+ "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motDePasse == null) ? 0 : motDePasse.hashCode());
		result = prime * result + ((nomUtilisateur == null) ? 0 : nomUtilisateur.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} 
		else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} 
		else if (!id.equals(other.id))
			return false;
		if (motDePasse == null) {
			if (other.motDePasse != null)
				return false;
		} else if (!motDePasse.equals(other.motDePasse))
			return false;
		if (nomUtilisateur == null) {
			if (other.nomUtilisateur != null)
				return false;
		} else if (!nomUtilisateur.equals(other.nomUtilisateur))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		return true;
	}
}
