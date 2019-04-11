package ch.hearc.spring.echoppe.data.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import ch.hearc.spring.echoppe.data.ArticleDAO;
import ch.hearc.spring.echoppe.model.Article;

@Profile("memory")
@Component
public class InMemoryArticleDAO implements ArticleDAO {

	private static List<Article> DATASOURCE = new ArrayList<>();

	static {
		DATASOURCE.add(new Article("produit 1", 12.50));
		DATASOURCE.add(new Article("produit 2", 18.0));

	}

	@Override
	public List<Article> findAll() {
		return DATASOURCE;
	}

	@Override
	public void save(@Valid Article product) {
		DATASOURCE.add(product);

	}

	@Override
	public Article findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
