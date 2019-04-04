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

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	@ManyToMany(mappedBy = "roles")
	private Set<Utilisateur> Utilisateurs;

	// Getters
	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public Set<Utilisateur> getUtilisateurs() {
		return Utilisateurs;
	}

	// Setters
	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setUtilisateurs(Set<Utilisateur> Utilisateurs) {
		this.Utilisateurs = Utilisateurs;
	}

	// Constructors
	public Role(Long id, String nom, Set<Utilisateur> Utilisateurs) {
		super();
		this.id = id;
		this.nom = nom;
		this.Utilisateurs = Utilisateurs;
	}

	public Role() {
	}

	// ToString, Hashcode, Equals
	@Override
	public String toString() {
		return "Role [id=" + id + ", nom=" + nom + ", Utilisateurs=" + Utilisateurs + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Utilisateurs == null) ? 0 : Utilisateurs.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		Role other = (Role) obj;
		if (Utilisateurs == null) {
			if (other.Utilisateurs != null)
				return false;
		} else if (!Utilisateurs.equals(other.Utilisateurs))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

}