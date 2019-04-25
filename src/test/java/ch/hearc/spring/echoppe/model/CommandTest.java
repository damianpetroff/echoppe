package ch.hearc.spring.echoppe.model;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommandTest {

	@Test
	public void givenCategory_whenCreateNewCategory_IsEqual() {
		Article article1 = new Article("article1", 36.0);
		Category cat1 = new Category("cat1");
		article1.setCategory(cat1);
		
		Article article2 = new Article("article1", 36.3);
		Category cat2 = new Category("cat2");
		article1.setCategory(cat2);
		
		ArticleCommand ac1 = new ArticleCommand(article1, 1);
		ArticleCommand ac2 = new ArticleCommand(article2, 2);
		
		List<ArticleCommand> acList = new ArrayList<>();
		acList.add(ac1);
		acList.add(ac2);
		
		Command command = new Command();
		command.addContent(ac1);
		command.addContent(ac2);
		assertTrue(command.getPrice() == (1.0 * 36.0 + 2.0 * 36.3));
		
		Payment payment = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			payment = new Payment(1, sdf.parse("04-04-2019 10:30:00"), 0);
			command.setDate(sdf.parse("04-04-2019 10:40:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		command.setPayment(payment);
		
		Utilisateur user = new Utilisateur();
		command.setUtilisateur(user);
		
		
		Command command2 = new Command();
		for(ArticleCommand ac : command.getContent())
		{
			command2.addContent(ac);
		}
		assertTrue(command2.getPrice() == command.getPrice());
		
		Payment payment2 = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			payment2 = new Payment(1, sdf.parse("04-04-2019 10:40:00"), 0);
			command2.setDate(sdf.parse("04-04-2019 10:50:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		command2.setPayment(payment2);
		command2.setUtilisateur(user);
			
		assertFalse(command.equals(command2));
	}
}
