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

@Entity
public class Rating {
	
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Min(1)
	@Max(5)
	private int rating;
	
	@NotNull
	@OneToOne
	private Utilisateur Utilisateur;
	
	@NotNull
	@ManyToOne
	private Article article;

	@Override
	public String toString() {
		return "Rating [id=" + id + ", rating=" + rating + ", Utilisateur=" + Utilisateur + ", article=" + article + "]";
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Utilisateur getUtilisateur() {
		return Utilisateur;
	}

	public void setUtilisateur(Utilisateur Utilisateur) {
		this.Utilisateur = Utilisateur;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getId() {
		return id;
	}

	public Rating(int id, int rating, Utilisateur Utilisateur,Article article) {
		super();
		this.id = id;
		this.rating = rating;
		this.Utilisateur = Utilisateur;
		this.article = article;
	}

	public Rating() {
		// TODO Auto-generated constructor stub
	}
	
	
}
