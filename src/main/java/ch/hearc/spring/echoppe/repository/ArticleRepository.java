package ch.hearc.spring.echoppe.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.spring.echoppe.model.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	Article findById(long id);
}
