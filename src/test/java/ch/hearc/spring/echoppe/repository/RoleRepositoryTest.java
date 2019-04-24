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
import ch.hearc.spring.echoppe.model.ArticleCommand;
import ch.hearc.spring.echoppe.model.Category;
import ch.hearc.spring.echoppe.model.Role;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	public void givenRole_whenPersistRole_theRoleIsPersisted() {
		Role roleTest = new Role("ROLE_TEST");
		
		entityManager.persist(roleTest);
		entityManager.flush();
		
		Optional<Role> roleRecherche = roleRepository.findById(roleTest.getId());
		
		assertTrue(roleRecherche.isPresent());
		assertTrue(roleRecherche.get().getId().equals(roleTest.getId()));
		assertTrue(roleRecherche.get().getNom().equals(roleTest.getNom()));
		
		assertThat(roleRecherche.get()).isNotNull();
	}
}
