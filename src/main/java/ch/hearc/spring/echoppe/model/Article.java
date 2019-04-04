package ch.hearc.spring.echoppe.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private BigDecimal price;
	@ManyToOne
	private Category category;
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
	private Set<ArticleCommand> articleCommand;

	// Constructors
	public Article(String name, BigDecimal price) {
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

	public BigDecimal getPrice() {
		return price;
	}

	public Category getCategory() {
		return category;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articleCommand == null) ? 0 : articleCommand.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		Article other = (Article) obj;
		if (articleCommand == null) {
			if (other.articleCommand != null)
				return false;
		} else if (!articleCommand.equals(other.articleCommand))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
}