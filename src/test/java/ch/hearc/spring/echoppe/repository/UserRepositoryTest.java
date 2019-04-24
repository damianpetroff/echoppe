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

import ch.hearc.spring.echoppe.model.Role;
import ch.hearc.spring.echoppe.model.Utilisateur;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void givenUser_whenPersistUser_theUserIsPersisted() {
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

		Optional<Utilisateur> userRecherche = userRepository.findById(utilisateur.getId());

		assertTrue(userRecherche.isPresent());
		
		Utilisateur utilisateur2 = userRecherche.get();
		assertTrue(utilisateur2.getId().equals(utilisateur.getId()));
		assertTrue(utilisateur2.getUsername().equals(utilisateur.getUsername()));
		assertTrue(utilisateur2.getEmail().equals(utilisateur.getEmail()));
		assertTrue(utilisateur2.getMotDePasse().equals(utilisateur.getMotDePasse()));
		assertTrue(utilisateur2.getRoles().equals(utilisateur.getRoles()));
		assertTrue(utilisateur2.hashCode() == utilisateur.hashCode());
		assertTrue(utilisateur2.equals(utilisateur));
		assertThat(utilisateur2).isNotNull();
	}
}
