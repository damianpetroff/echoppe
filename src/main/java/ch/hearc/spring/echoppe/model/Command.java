package ch.hearc.spring.echoppe.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
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

@Entity
public class Command {

	// Attributes
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@OneToOne
	@JoinColumn
	private Payment payment;
	@OneToMany(mappedBy = "command", cascade = CascadeType.ALL)
	private Set<ArticleCommand> articleCommand;
	@NotNull
	@DecimalMax("1000.0")
	@DecimalMin("0.0")
	private BigDecimal price;

	// Constructors
	public Command(Set<ArticleCommand> setArticleCommand, Payment payment, BigDecimal price) {
		super();
		this.payment = payment;
		this.price = price;
	}

	public Command() {
	}

	// Getters
	public BigDecimal getPrice() {
		return price;
	}

	public Payment getPayment() {
		return payment;
	}

	// Setters
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	// ToString
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
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
}