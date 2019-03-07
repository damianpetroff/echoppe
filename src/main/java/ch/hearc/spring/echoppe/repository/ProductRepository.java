package ch.hearc.spring.echoppe.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.spring.echoppe.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
