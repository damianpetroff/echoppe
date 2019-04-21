package ch.hearc.spring.echoppe.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import ch.hearc.spring.echoppe.model.Article;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
	Article findById(long id);
	Article findByName(String name);
	Page<Article> findAllByNameIgnoreCaseContaining(String name, Pageable pageable);
}
