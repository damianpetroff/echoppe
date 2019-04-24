package ch.hearc.spring.echoppe.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import ch.hearc.spring.echoppe.model.Article;
import ch.hearc.spring.echoppe.model.Category;
import ch.hearc.spring.echoppe.model.Comment;
import ch.hearc.spring.echoppe.model.Role;
import ch.hearc.spring.echoppe.model.Utilisateur;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CommentRepository commentRepository;

	@Test
	public void givenComment_whenPersistComment_theCommentIsPersisted() {
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

		Role roleTest = new Role("ROLE_TEST");

		entityManager.persist(roleTest);
		entityManager.flush();

		HashSet<Role> testRoles = new HashSet<Role>();
		testRoles.add(roleTest);

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setEmail("test@test.com");
		utilisateur.setUsername("test");
		utilisateur.setMotDePasse("test");
		utilisateur.setRoles(testRoles);

		entityManager.persist(utilisateur);
		entityManager.flush();

		Comment comment = new Comment();
		comment.setArticle(article);
		comment.setComment("comment_test");
		comment.setUtilisateur(utilisateur);

		entityManager.persist(comment);
		entityManager.flush();

		Optional<Comment> commentRecherche = commentRepository.findById(comment.getId());

		assertTrue(commentRecherche.isPresent());
		
		Comment comment2 = commentRecherche.get();
		assertTrue(comment2.getId().equals(comment.getId()));
		assertTrue(comment2.getArticle().equals(comment.getArticle()));
		assertTrue(comment2.getComment().equals(comment.getComment()));
		assertTrue(comment2.getUtilisateur().equals(comment.getUtilisateur()));
		assertTrue(comment2.toString() == comment.toString());
		assertTrue(comment2.hashCode() == comment.hashCode());
		assertTrue(comment2.equals(comment));
		assertThat(comment2).isNotNull();
	}
}
