package ch.hearc.spring.echoppe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Comment {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 3)
	private String comment;

	@NotNull
	@OneToOne
	private Utilisateur Utilisateur;

	@ManyToOne
	@Cascade({ CascadeType.DELETE })
	@NotNull
	private Article article;

	// Constructors
	public Comment(String comment, Utilisateur Utilisateur, Article article) {
		super();
		this.comment = comment;
		this.Utilisateur = Utilisateur;
		this.article = article;
	}

	public Comment() {
	}

	// Getters
	public Long getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public Utilisateur getUtilisateur() {
		return Utilisateur;
	}

	public Article getArticle() {
		return article;
	}

	// Setters
	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setUtilisateur(Utilisateur Utilisateur) {
		this.Utilisateur = Utilisateur;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	// ToString
	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", Utilisateur=" + Utilisateur + ", article=" + article
				+ "]";
	}
}
