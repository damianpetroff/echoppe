package ch.hearc.spring.echoppe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class ArticleCommand {

	// Attributes
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@OneToOne
	@Cascade({ CascadeType.DELETE })
	private Article article;

	private int quantity;

	// Constructor
	public ArticleCommand(Article article, int quantity) {
		super();
		this.article = article;
		this.quantity = quantity;
	}

	public ArticleCommand() {

	}

	// Getters
	public Long getId() {
		return id;
	}

	public Article getArticle() {
		return article;
	}

	public int getQuantity() {
		return quantity;
	}

	// Setters
	public void setArticle(Article article) {
		this.article = article;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// ToString
	@Override
	public String toString() {
		return "ArticleCommand [id=" + id + ", article=" + article + ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + quantity;
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
		ArticleCommand other = (ArticleCommand) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} 
		else if (!article.equals(other.article))
			return false;

		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;

		if (quantity != other.quantity)
			return false;
		return true;
	}

}
