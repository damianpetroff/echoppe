package ch.hearc.spring.echoppe.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@Entity
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min = 2)
	private String nomUtilisateur;

	@Email
	private String email;

	private String motDePasse;
	
	@ManyToMany
	private Set<Role> roles;

//	public Utilisateur(int id, String nomUtilisateur, String email, String motDePasse) {
//		
//		this.id = id;
//		this.nomUtilisateur = nomUtilisateur;
//		this.email = email;
//		this.motDePasse = motDePasse;
//	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return nomUtilisateur;
	}

	public void setUsername(String username) {
		this.nomUtilisateur = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + nomUtilisateur + ", email=" + email + ", motDePasse=" + motDePasse + "]";
	}


	//Constructeur par défaut
	public Utilisateur() {

	}
	


}
