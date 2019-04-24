package ch.hearc.spring.echoppe.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import ch.hearc.spring.echoppe.model.Article;
import ch.hearc.spring.echoppe.model.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ArticleRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ArticleRepository articleRepository;

	@Test
	public void givenArticle_whenPersistArticle_theArticleIsPersisted() {
		Category category = new Category();
		category.setName("category test");

		entityManager.persist(category);
		entityManager.flush();

		Article article = new Article();
		article.setPrice(1);
		article.setName("test article");
		article.setCategory(category);

		entityManager.persist(article);
		entityManager.flush();

		Optional<Article> articleRecherche = articleRepository.findById(article.getId());

		assertTrue(articleRecherche.isPresent());
		
		Article article2 = articleRecherche.get();
		assertTrue(article2.getId().equals(article.getId()));
		assertTrue(article2.getPrice() == article.getPrice());
		assertTrue(article2.getName().equals(article.getName()));
		assertTrue(article2.getCategory().equals(article.getCategory()));
		assertTrue(article2.hashCode() == article.hashCode());
		assertTrue(article2.equals(article));

		assertThat(articleRecherche.get()).isNotNull();
	}
}
