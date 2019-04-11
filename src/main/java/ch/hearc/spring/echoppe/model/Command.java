package ch.hearc.spring.echoppe.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@OneToOne
	@JoinColumn
	private Payment payment;

	@NotNull
	private HashMap<Article, Integer> content;

	@NotNull
	@DecimalMax("1000.0")
	@DecimalMin("0.0")
	private double price;

	// Constructors
	public Command() {
		this.content = new HashMap<Article, Integer>();
		this.price = 0.0;
	}

	// Getters
	public double getPrice() {
		return price;
	}

	public Payment getPayment() {
		return payment;
	}

	public HashMap<Article, Integer> getContent() {
		return content;
	}

	// Setters
	public void setPrice(double price) {
		this.price = price;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setContent(HashMap<Article, Integer> content) {
		this.content = content;
	}

	// Methods
	public void addContent(Article article, int quantity) {
		content.put(article, quantity);
		computeCommandPrice();
	}

	public void clearContent() {
		content.clear();
		computeCommandPrice();
	}

	private void computeCommandPrice() {
		double sum = 0.0;
		for (Map.Entry<Article, Integer> entry : content.entrySet()) {
			Article article = entry.getKey();
			Integer quantity = entry.getValue();
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
		result = prime * result + ((content == null) ? 0 : content.hashCode());
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
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
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
