package ch.hearc.spring.echoppe.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.spring.echoppe.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	List<Category> findAll();
}
