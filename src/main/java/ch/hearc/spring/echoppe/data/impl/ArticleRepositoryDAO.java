package ch.hearc.spring.echoppe.data.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hearc.spring.echoppe.data.ArticleDAO;
import ch.hearc.spring.echoppe.model.Article;
import ch.hearc.spring.echoppe.repository.ArticleRepository;

@Component
public class ArticleRepositoryDAO implements ArticleDAO{

	@Autowired
	private ArticleRepository prepo;
	
	@Override
	public List<Article> findAll() {
		
		List<Article> products = new ArrayList<>();
		
		prepo.findAll().forEach(products::add);
		
		return products;
	}

	@Override
	public void save(@Valid Article product) {
		
		prepo.save(product);
		
	}

}
