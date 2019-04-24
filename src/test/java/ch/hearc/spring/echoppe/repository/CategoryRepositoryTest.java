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

import ch.hearc.spring.echoppe.model.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void givenCategory_whenPersistCategory_theCategoryIsPersisted() {
		Category category = new Category();
		category.setName("category test");

		entityManager.persist(category);
		entityManager.flush();

		Optional<Category> categoryRecherche = categoryRepository.findById(category.getId());

		assertTrue(categoryRecherche.isPresent());
		assertTrue(categoryRecherche.get().getId().equals(category.getId()));
		assertTrue(categoryRecherche.get().getName().equals(category.getName()));

		assertThat(categoryRecherche.get()).isNotNull();
	}
}
