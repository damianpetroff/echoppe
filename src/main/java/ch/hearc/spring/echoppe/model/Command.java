package ch.hearc.spring.echoppe.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
public class Command {

	// Attributes
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@NotNull
	@ManyToOne
	private Utilisateur utilisateur;

	@NotNull
	private Date date;

	@OneToOne
	@JoinColumn
	private Payment payment;

	@OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE)
	private List<ArticleCommand> articleCommand;

	@NotNull
	@DecimalMax("1000000.0")
	@DecimalMin("0.0")
	private double price;

	// Constructors
	public Command() {
		this.articleCommand = new ArrayList<ArticleCommand>();
		this.utilisateur = new Utilisateur();
		this.price = 0.0;
		this.date = new Date();
	}

	// Getters
	public double getPrice() {
		computeCommandPrice();
		return price;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public Payment getPayment() {
		return payment;
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public List<ArticleCommand> getContent() {
		return articleCommand;
	}

	// Setters
	public void setPrice(double price) {
		this.price = price;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setContent(List<ArticleCommand> articleCommand) {
		this.articleCommand = articleCommand;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	// Methods
	public void addContent(ArticleCommand newArticleCommand) {
		articleCommand.add(newArticleCommand);
		computeCommandPrice();
	}

	public void clearContent() {
		articleCommand.clear();
		computeCommandPrice();
	}

	private void computeCommandPrice() {
		double sum = 0.0;
		for (ArticleCommand articleCommand : articleCommand) {
			Article article = articleCommand.getArticle();
			Integer quantity = articleCommand.getQuantity();
			sum += article.getPrice() * quantity;
		}
		this.price = sum;
	}

	// ToString, Hashcode, Equals

	@Override
	public String toString() {
		return "Command [id=" + id + ", utilisateur=" + utilisateur + ", date=" + date + ", payment=" + payment
				+ ", articleCommand=" + articleCommand + ", price=" + price + "]";
	}
}
