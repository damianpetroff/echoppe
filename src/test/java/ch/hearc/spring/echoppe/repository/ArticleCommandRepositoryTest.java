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
import ch.hearc.spring.echoppe.model.ArticleCommand;
import ch.hearc.spring.echoppe.model.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ArticleCommandRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ArticleCommandRepository articleCommandRepository;

	@Test
	public void givenArticleCommand_whenPersistArticleCommand_theArticleCommandIsPersisted() {
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

		ArticleCommand articleCommand = new ArticleCommand();
		articleCommand.setArticle(article);
		articleCommand.setQuantity(5);

		entityManager.persist(articleCommand);
		entityManager.flush();

		Optional<ArticleCommand> articleCommandRecherche = articleCommandRepository.findById(articleCommand.getId());

		assertTrue(articleCommandRecherche.isPresent());
		
		ArticleCommand articleCommand2 = articleCommandRecherche.get();
		assertTrue(articleCommand2.getId().equals(articleCommand.getId()));
		assertTrue(articleCommand2.getArticle().equals(articleCommand.getArticle()));
		assertTrue(articleCommand2.getQuantity() == articleCommand.getQuantity());
		assertTrue(articleCommand2.equals(articleCommand));
		assertTrue(articleCommand2.hashCode() == articleCommand.hashCode());		
		assertThat(articleCommand2).isNotNull();
	}
}
