package ch.hearc.spring.echoppe.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

//	@OneToOne
//	@JoinColumn
//	private Paiement paiement;
//
//	@OneToMany
//	@JoinColumn
//	private List<Article> articles;


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
	
//	public Paiement getPaiement() {
//		return paiement;
//	}
//
//	public void setPaiement(Paiement paiement) {
//		this.paiement = paiement;
//	}
//	
//	public List<Article> getArticles() {
//		return articles;
//	}
//
//	public void setArticles(List<Article> articles) {
//		this.articles = articles;
//	}
	
	@Override
	public String toString() {
		String s = "Command [ paiement :articles : [";
//		articles.forEach(a->s+=a+", ");
		s += "], quantity :" + quantity + ", price :" + price;
		return s;
	}
	
	
}