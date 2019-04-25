package ch.hearc.spring.echoppe.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Payment {

	// Attributes
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@NotNull
	private int status;
	@NotNull
	private Date date;
	@NotNull
	private int method;

	// Getters
	public Long getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	public Date getDate() {
		return date;
	}

	public int getMethod() {
		return method;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	// Constructor
	public Payment(@NotNull int status, @NotNull Date date, @NotNull int method) {
		super();
		this.status = status;
		this.date = date;
		this.method = method;
	}

	public Payment() {
	}

	// ToString, Hashcode, Equals
	@Override
	public String toString() {
		return "Payment [id=" + id + ", status=" + status + ", method=" + method + "]";
	}
}
