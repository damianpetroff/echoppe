package ch.hearc.spring.echoppe.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Article {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@NotNull
    @Size(min=2, max=30)
	private String name;
	
	@NotNull
	@DecimalMin("0.0") 
	private BigDecimal price;
	
	@ManyToOne
    @JoinColumn
	private Category category;

	public Article(String name, BigDecimal price, Category category) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
	}
	
	public Article() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}	
	

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", category ="+ category +"]";
	}

}