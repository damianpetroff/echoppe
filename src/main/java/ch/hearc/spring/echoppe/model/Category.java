package ch.hearc.spring.echoppe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Category {

	@NotNull
	@Size(min = 2, max = 30)
	private String name;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	public Category(String name) {
		super();
		this.name = name;
	}

	public Category() {
		// TODO Auto-generated constructor stub
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

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
