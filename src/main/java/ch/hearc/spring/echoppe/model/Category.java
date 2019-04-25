package ch.hearc.spring.echoppe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Category {

	// Attributes
	@NotNull
	@Size(min = 2, max = 30)
	private String name;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	// Constructors
	public Category(String name) {
		super();
		this.name = name;
	}

	public Category() {
	}

	// Getters
	public String getName() {
		return name;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// ToString, Hashcode, Equals
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Category [id=");
		sb.append(id);
		sb.append(", name=");
		sb.append(name);
		sb.append("]");
		return sb.toString();
	}
}
