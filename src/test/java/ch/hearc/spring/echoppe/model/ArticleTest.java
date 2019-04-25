package ch.hearc.spring.echoppe.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ArticleTest {

	@Test
	public void givenCategory_whenCreateNewCategory_IsEqual() {
		String name = "newarticle";
		double price = 36.0;

		Article article = new Article();
		article.setName(name);
		assertTrue(article.getName() == name);

		Category cat = new Category("cat");
		article.setCategory(cat);
		assertTrue(article.getCategory().getName() == cat.getName());

		article.setPrice(price);
		assertTrue(article.getPrice() == price);

		Article article2 = new Article();
		Category cat2 = new Category("cat2");
		article2.setName("newarticle2");
		article2.setCategory(cat2);
		article2.setPrice(36.3);

		assertFalse(article.equals(article2));
	}
}
