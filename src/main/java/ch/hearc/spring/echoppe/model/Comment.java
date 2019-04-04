package ch.hearc.spring.echoppe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Comment {

	// Attributes
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Size(min = 3)
	private String comment;
	@NotNull
	@OneToOne
	private Utilisateur Utilisateur;
	@ManyToOne
	@NotNull
	private Article article;

	// Constructors
	public Comment(int id, String comment, Utilisateur Utilisateur, Article article) {
		super();
		this.id = id;
		this.comment = comment;
		this.Utilisateur = Utilisateur;
		this.article = article;
	}

	public Comment() {
	}

	// Getters
	public int getId() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Utilisateur == null) ? 0 : Utilisateur.hashCode());
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + id;
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
		Comment other = (Comment) obj;
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
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
}
