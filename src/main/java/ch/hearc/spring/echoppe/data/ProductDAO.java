package ch.hearc.spring.echoppe.data;

import java.util.List;

import javax.validation.Valid;

import ch.hearc.spring.echoppe.model.Product;

public interface ProductDAO {

	List<Product> findAll();

	void save(@Valid Product product);

}
