package ch.hearc.spring.echoppe.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ch.hearc.spring.echoppe.model.Article;
import ch.hearc.spring.echoppe.model.Category;
import ch.hearc.spring.echoppe.repository.ArticleRepository;
import ch.hearc.spring.echoppe.repository.CategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ArticleRepository articleRepository;

	@MockBean
	CategoryRepository categoryRepository;

	@Before
	public void setUp() {
		Category c1 = new Category();
		c1.setName("c1");

		Category c2 = new Category();
		c1.setName("c2");

		List<Category> listCategory = new ArrayList<>();
		listCategory.add(c1);
		listCategory.add(c2);

		Mockito.when(categoryRepository.findAll()).thenReturn(listCategory);

		Article a1 = new Article();
		a1.setName("a1");
		a1.setCategory(c1);
		a1.setPrice(10);

		Article a2 = new Article();
		a2.setName("a2");
		a2.setCategory(c2);
		a2.setPrice(20);

		List<Article> listArticle = new ArrayList<>();
		listArticle.add(a1);
		listArticle.add(a2);

		Mockito.when(articleRepository.findAll()).thenReturn(listArticle);
	}

	@Test
	public void whenArticleController_thenResponseIsCorrect() throws Exception {
		mockMvc.perform(get("/articles")).andExpect(status().isOk()).andExpect(view().name("articles"));
	}
	
	@Test
	public void whenArticleControllerDetail_thenResponseIsCorrect() throws Exception {
		mockMvc.perform(get("/article/{id}", "1")).andExpect(status().isOk()).andExpect(view().name("article"));
	}
	
	@Test
	@WithMockUser(username = "admin_test", roles = { "ROLE_ADMIN" })
	public void whenArticleControllerAdd_thenResponseIsCorrect() throws Exception {
		mockMvc.perform(get("/input_articles")).andExpect(status().isOk()).andExpect(view().name("input_articles"));
	}

}
