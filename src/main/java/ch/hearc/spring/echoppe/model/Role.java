package ch.hearc.spring.echoppe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;

	// Getters
	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	// Setters
	public void setNom(String nom) {
		this.nom = nom;
	}

	// Constructors
	public Role(String nom) {
		super();
		this.nom = nom;
	}

	public Role() {
	}

	// ToString, Hashcode, Equals
	@Override
	public String toString() {
		return "Role [id=" + id + ", nom=" + nom + "]";
	}
}