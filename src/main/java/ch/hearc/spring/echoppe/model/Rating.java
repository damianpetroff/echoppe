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
	@Cascade({CascadeType.DELETE})
	private Article article;

	// Getters
	public int getId() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Utilisateur == null) ? 0 : Utilisateur.hashCode());
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + id;
		result = prime * result + rating;
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
		Rating other = (Rating) obj;
		if (Utilisateur == null) {
			if (other.Utilisateur != null)
				return false;
		} else if (!Utilisateur.equals(other.Utilisateur))
			return false;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (id != other.id)
			return false;
		if (rating != other.rating)
			return false;
		return true;
	}

}
