package ch.hearc.spring.echoppe.data.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import ch.hearc.spring.echoppe.data.ProductDAO;
import ch.hearc.spring.echoppe.model.Product;

@Profile("memory")
@Component
public class InMemoryProductDAO implements ProductDAO{

	private static List<Product> DATASOURCE = new ArrayList<>();
	
	
	static{
		DATASOURCE.add(new Product("produit 1",new BigDecimal("12.50")));
		DATASOURCE.add(new Product("produit 2",new BigDecimal(18)));
		
	}


	@Override
	public List<Product> findAll() {
		return DATASOURCE;
	}


	@Override
	public void save(@Valid Product product) {
		DATASOURCE.add(product);
		
	}


	
	
	
}
