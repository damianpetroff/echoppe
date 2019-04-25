package ch.hearc.spring.echoppe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Article {

	// Attributes
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@NotNull
	@Size(min = 2, max = 30)
	private String name;

	@NotNull
	@DecimalMin("0.0")
	private double price;

	@ManyToOne
	private Category category;

	// Constructors
	public Article(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Article() {
	}

	// Getters
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public Category getCategory() {
		return category;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	// ToString, Hashcode, Equals
	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", category =" + category + "]";
	}
}