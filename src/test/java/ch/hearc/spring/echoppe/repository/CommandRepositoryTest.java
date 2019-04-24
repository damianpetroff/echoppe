package ch.hearc.spring.echoppe.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import ch.hearc.spring.echoppe.model.Command;
import ch.hearc.spring.echoppe.model.Payment;
import ch.hearc.spring.echoppe.model.Role;
import ch.hearc.spring.echoppe.model.Utilisateur;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommandRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CommandRepository commandRepository;

	@Test
	public void givenCommand_whenPersistCommand_theCommandIsPersisted() {
		Category category = new Category();
		category.setName("category test");

		entityManager.persist(category);
		entityManager.flush();

		List<ArticleCommand> articlesCommand = new ArrayList<ArticleCommand>();
		for (int i = 0; i < 5; i++) {
			Article a = new Article();
			a.setName("article" + Integer.toString(i));
			a.setCategory(category);
			a.setPrice(i);

			entityManager.persist(a);
			entityManager.flush();

			ArticleCommand articleCommand = new ArticleCommand();
			articleCommand.setArticle(a);
			articleCommand.setQuantity(i);

			entityManager.persist(articleCommand);
			entityManager.flush();

			articlesCommand.add(articleCommand);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

		Payment payment = new Payment();
		try {
			payment.setDate(sdf.parse("04-04-2019 10:30:00"));
		} catch (ParseException e) {

		}
		payment.setMethod(0);
		payment.setStatus(0);

		entityManager.persist(payment);
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

		Command command = new Command();
		command.setContent(articlesCommand);
		try {
			command.setDate(sdf.parse("04-04-2019 10:30:00"));
		} catch (ParseException e) {

		}
		command.setPayment(payment);
		command.setPrice(1);
		command.setUtilisateur(utilisateur);

		entityManager.persist(command);
		entityManager.flush();

		Optional<Command> commandRecherche = commandRepository.findById(command.getId());

		assertTrue(commandRecherche.isPresent());
		
		Command command2 = commandRecherche.get();
		assertTrue(command2.getId().equals(command.getId()));
		assertTrue(command2.getContent().equals(command.getContent()));
		assertTrue(command2.getDate().equals(command.getDate()));
		assertTrue(command2.getPayment().equals(command.getPayment()));
		assertTrue(command2.getPrice() == command.getPrice());
		assertTrue(command2.hashCode() == command.hashCode());
		assertTrue(command2.equals(command));
		assertTrue(command2.getUtilisateur().equals(command.getUtilisateur()));

		assertThat(command2).isNotNull();
	}
}
