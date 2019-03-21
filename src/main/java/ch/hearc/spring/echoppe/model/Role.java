package ch.hearc.spring.echoppe.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nom;

	@ManyToMany(mappedBy = "roles")
	private Set<Utilisateur> Utilisateurs;

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

	public Set<Utilisateur> getUtilisateurs() {
		return Utilisateurs;
	}

	public void setUtilisateurs(Set<Utilisateur> Utilisateurs) {
		this.Utilisateurs = Utilisateurs;
	}

	public Role() {
	}

//	public Role(Long id, String nom, Set<Utilisateur> Utilisateurs) {
//		super();
//		this.id = id;
//		this.nom = nom;
//		this.Utilisateurs = Utilisateurs;
//	}

	// Constructeur par défaut

}