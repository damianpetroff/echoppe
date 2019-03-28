package ch.hearc.spring.echoppe.data;

import java.util.List;

import javax.validation.Valid;

import ch.hearc.spring.echoppe.model.Category;

public interface CategoryDAO {

	List<Category> findAll();

	void save(@Valid Category category);

}
