package ch.hearc.spring.echoppe.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Command {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@NotNull
	private int quantity;

	@OneToOne
	@JoinColumn
	private Payment payment;

	@OneToMany
	private List<Article> articles;


	@NotNull
	@DecimalMax("1000.0") @DecimalMin("0.0") 
	private BigDecimal price;
	
	public Command(BigDecimal price) {
		super();
		this.price = price;
	}
	
	public Command() {
		// TODO Auto-generated constructor stub
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	@Override
	public String toString() {
		StringBuilder s =new StringBuilder( "Command [ payment :");
		s.append( payment).append( "articles : [");
		articles.forEach(a->s.append(a).append(", "));
		s.append("], quantity :").append(quantity ).append( ", price :").append(price);
		return s.toString();
	}
	
	
}