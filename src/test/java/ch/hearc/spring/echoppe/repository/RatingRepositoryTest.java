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
import ch.hearc.spring.echoppe.model.Rating;
import ch.hearc.spring.echoppe.model.Role;
import ch.hearc.spring.echoppe.model.Utilisateur;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RatingRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private RatingRepository ratingRepository;

	@Test
	public void givenRating_whenPersistRating_theRatingIsPersisted() {
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

		Rating rating = new Rating();
		rating.setArticle(article);
//		rating.setRating(6); // Won't pass cause the range of rating is 1 to 5
		rating.setRating(3); // Right insert 
		rating.setUtilisateur(utilisateur);

		entityManager.persist(rating);
		entityManager.flush();

		Optional<Rating> ratingRecherche = ratingRepository.findById(rating.getId());

		assertTrue(ratingRecherche.isPresent());
		assertTrue(ratingRecherche.get().getId().equals(rating.getId()));
		assertTrue(ratingRecherche.get().getArticle().equals(rating.getArticle()));
		assertTrue(ratingRecherche.get().getRating() == rating.getRating());
		assertTrue(ratingRecherche.get().getUtilisateur().equals(rating.getUtilisateur()));

		assertThat(ratingRecherche.get()).isNotNull();
	}
}
