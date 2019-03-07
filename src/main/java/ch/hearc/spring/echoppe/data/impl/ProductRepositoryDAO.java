package ch.hearc.spring.echoppe.data.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hearc.spring.echoppe.data.ProductDAO;
import ch.hearc.spring.echoppe.model.Product;
import ch.hearc.spring.echoppe.repository.ProductRepository;

@Component
public class ProductRepositoryDAO implements ProductDAO{

	@Autowired
	private ProductRepository prepo;
	
	@Override
	public List<Product> findAll() {
		
		List<Product> products = new ArrayList<>();
		
		prepo.findAll().forEach(products::add);
		
		return products;
	}

	@Override
	public void save(@Valid Product product) {
		
		prepo.save(product);
		
	}

}
