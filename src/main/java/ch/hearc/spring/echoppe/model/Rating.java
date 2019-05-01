package ch.hearc.spring.echoppe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Rating {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Min(1)
	@Max(5)
	private int rating;

	@NotNull
	@OneToOne
	private Utilisateur Utilisateur;

	@NotNull
	@ManyToOne
	@Cascade({ CascadeType.ALL })
	private Article article;

	// Getters
	public Long getId() {
		return id;
	}

	public int getRating() {
		return rating;
	}

	public Utilisateur getUtilisateur() {
		return Utilisateur;
	}

	public Article getArticle() {
		return article;
	}

	// Setters
	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setUtilisateur(Utilisateur Utilisateur) {
		this.Utilisateur = Utilisateur;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	// Constructor
	public Rating(int rating, Utilisateur Utilisateur, Article article) {
		super();
		this.rating = rating;
		this.Utilisateur = Utilisateur;
		this.article = article;
	}

	public Rating() {
	}

	// ToString, Hashcode, Equals
	@Override
	public String toString() {
		return "Rating [id=" + id + ", rating=" + rating + ", Utilisateur=" + Utilisateur + ", article=" + article
				+ "]";
	}
}
