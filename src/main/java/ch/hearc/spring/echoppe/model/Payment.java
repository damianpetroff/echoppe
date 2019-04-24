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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + method;
		result = prime * result + status;
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
		Payment other = (Payment) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (method != other.method)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}
