package ch.hearc.spring.echoppe.data.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hearc.spring.echoppe.data.CategoryDAO;
import ch.hearc.spring.echoppe.model.Category;
import ch.hearc.spring.echoppe.repository.CategoryRepository;

@Component
public class CategoryRepositoryDAO implements CategoryDAO {

	@Autowired
	private CategoryRepository prepo;

	@Override
	public List<Category> findAll() {

		List<Category> products = new ArrayList<>();

		prepo.findAll().forEach(products::add);

		return products;
	}

	@Override
	public void save(@Valid Category product) {

		prepo.save(product);

	}

}
