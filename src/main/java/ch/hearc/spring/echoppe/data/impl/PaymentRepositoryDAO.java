package ch.hearc.spring.echoppe.data.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hearc.spring.echoppe.data.PaymentDAO;
import ch.hearc.spring.echoppe.model.Payment;
import ch.hearc.spring.echoppe.repository.PaymentRepository;

@Component
public class PaymentRepositoryDAO implements PaymentDAO{

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public List<Payment> findAll() {
		
		List<Payment> payments = new ArrayList<>();
		
		paymentRepository.findAll().forEach(payments::add);
		
		return payments;
	}
	
	@Override
	public Payment findById(long id) {
		return paymentRepository.findById(id);
	}

	@Override
	public void save(@Valid Payment product) {
		
		paymentRepository.save(product);
		
	}

}
