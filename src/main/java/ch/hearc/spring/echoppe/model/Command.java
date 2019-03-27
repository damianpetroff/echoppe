package ch.hearc.spring.echoppe.model;

import java.math.BigDecimal;
import java.util.List;
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
import javax.validation.constraints.Size;

@Entity
public class Command {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	

	@OneToOne
	@JoinColumn
	private Payment payment;

    @OneToMany(mappedBy = "command", cascade = CascadeType.ALL)
    private Set<ArticleCommand> articleCommand;


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
	

	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	

	
	@Override
	public String toString() {
		StringBuilder s =new StringBuilder( "Command [ payment :");
		s.append( payment).append( "], price :").append(price);
		return s.toString();
	}
	
	
}