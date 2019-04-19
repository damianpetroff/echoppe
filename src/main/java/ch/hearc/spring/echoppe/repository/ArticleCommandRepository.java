package ch.hearc.spring.echoppe.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.spring.echoppe.model.ArticleCommand;


public interface ArticleCommandRepository extends CrudRepository<ArticleCommand, Long> {
	
}
