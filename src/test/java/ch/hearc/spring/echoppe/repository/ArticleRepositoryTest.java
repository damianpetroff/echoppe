package ch.hearc.spring.echoppe.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import ch.hearc.spring.echoppe.model.Article;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ArticleRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Test
	public void givenArticle_whenPersistArticle_theArticleIsPersisted() {
		Article article = new Article();
		article.setPrice(1);
		article.setName("test article");
		
		entityManager.persist(article);
		entityManager.flush();
		
		Optional<Article> articleRecherche = articleRepository.findById(article.getId());
		
		assertTrue(articleRecherche.isPresent());
		assertTrue(articleRecherche.get().getId().equals(article.getId()));
		
		assertThat(articleRecherche.get()).isNotNull();
	}

}
