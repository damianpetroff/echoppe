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
	
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Size(min=3)
	private String comment;
	@NotNull
	@OneToOne
	private Utilisateur Utilisateur;
	@ManyToOne
	@NotNull
	private Article article;
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", Utilisateur=" + Utilisateur + ", article=" + article + "]";
	}

	public Comment(int id, String comment, Utilisateur Utilisateur,Article article) {
		super();
		this.id = id;
		this.comment = comment;
		this.Utilisateur = Utilisateur;
		this.article = article;
	}

	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	
}
