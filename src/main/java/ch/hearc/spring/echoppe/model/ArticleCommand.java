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

}
