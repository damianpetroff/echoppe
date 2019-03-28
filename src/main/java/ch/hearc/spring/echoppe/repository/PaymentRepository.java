package ch.hearc.spring.echoppe.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.spring.echoppe.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long>{
	Payment findById(long id);
}
