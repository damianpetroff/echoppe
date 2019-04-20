package ch.hearc.spring.echoppe.model;

import java.util.ArrayList;
import java.util.List;

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
	private Utilisateur Utilisateur;

	@OneToOne
	@JoinColumn
	private Payment payment;

	@OneToMany
	private List<ArticleCommand> articleCommand;

	@NotNull
	@DecimalMax("1000.0")
	@DecimalMin("0.0")
	private double price;

	// Constructors
	public Command() {
		this.articleCommand = new ArrayList<ArticleCommand>();
		this.price = 0.0;
	}

	// Getters
	public double getPrice() {
		computeCommandPrice();
		return price;
	}
	
	public Utilisateur getUtilisateur() {
		return Utilisateur;
	}

	public Payment getPayment() {
		return payment;
	}
	
	public Long getId() {
		return id;
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
	
	// Setters
	public void setUtilisateur(Utilisateur Utilisateur) {
		this.Utilisateur = Utilisateur;
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
		StringBuilder s = new StringBuilder("Command [ payment :");
		s.append(payment).append("], price :").append(price);
		return s.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articleCommand == null) ? 0 : articleCommand.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((payment == null) ? 0 : payment.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Command other = (Command) obj;
		if (articleCommand == null) {
			if (other.articleCommand != null)
				return false;
		} else if (!articleCommand.equals(other.articleCommand))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (payment == null) {
			if (other.payment != null)
				return false;
		} else if (!payment.equals(other.payment))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

}
