package ch.hearc.spring.echoppe.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryTest {

	@Test
	public void givenCategory_whenCreateNewCategory_IsEqual() {
		String name = "newcat";
		Long id = (long) 42;
		
		Category category = new Category();	
		category.setName(name);
		assertTrue(category.getName() == name);
		
		category.setId(id);
		assertTrue(category.getId() == id);
	
		Category category2 = new Category();
		
		category2.setId((long)43);
		category2.setName("newcat");
		
		assertFalse(category.equals(category2));
	}
}
