package ch.hearc.spring.echoppe.data;

import java.util.List;

import javax.validation.Valid;

import ch.hearc.spring.echoppe.model.Payment;

public interface PaymentDAO {

	List<Payment> findAll();
	
	Payment findById(long id);

	void save(@Valid Payment payment);

}
