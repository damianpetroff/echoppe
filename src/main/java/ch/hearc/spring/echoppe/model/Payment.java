package ch.hearc.spring.echoppe.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Payment {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	@NotNull
	private Long userId;
	
	@NotNull
	private int status;
	
	@NotNull
	private Date date;
	
	@NotNull
	private int method;
	
	
	@NotNull
	@DecimalMax("1000.0") @DecimalMin("0.0") 
	private BigDecimal price;
	
	public Payment(String name, BigDecimal price) {
		super();
	}
	
	public Payment() {
		// TODO Auto-generated constructor stub
	}

	public void setName(String name) {
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", userId=" + userId + ", status=" + status + ", method=" + method + ", price="
				+ price + "]";
	}

	public Payment(Long id, @NotNull Long userId, @NotNull int status, Date date, @NotNull int method,
			@NotNull @DecimalMax("1000.0") @DecimalMin("0.0") BigDecimal price) {
		super();
		this.id = id;
		this.userId = userId;
		this.status = status;
		this.date = date;
		this.method = method;
		this.price = price;
	}


	
	
}
