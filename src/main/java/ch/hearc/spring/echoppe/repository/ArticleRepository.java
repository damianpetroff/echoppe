package ch.hearc.spring.echoppe.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import ch.hearc.spring.echoppe.model.Article;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
	Article findById(long id);
	Article findByName(String name);
	List<Article> findAll();
	Page<Article> findAllByNameIgnoreCaseContaining(String name, Pageable pageable);
	Page<Article> findAllByCategoryNameIgnoreCaseContaining(String query, Pageable page);
	Page<Article> findAllByPriceLessThan(Double double1, Pageable page);
	Page<Article> findAllByPriceGreaterThan(Double double1, Pageable page);
}
