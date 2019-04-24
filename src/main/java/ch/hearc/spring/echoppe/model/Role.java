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
		return "Role [id=" + id + ", nom=" + nom +"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} 
		else if (!id.equals(other.id))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} 
		else if (!nom.equals(other.nom))
			return false;
		
		return true;
	}




}