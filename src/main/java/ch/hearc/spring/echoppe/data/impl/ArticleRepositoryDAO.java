package ch.hearc.spring.echoppe.data.impl;

import java.util.ArrayList;
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
	private ArticleRepository articleRepository;
	
	@Override
	public List<Article> findAll() {
		
		List<Article> products = new ArrayList<>();
		
		articleRepository.findAll().forEach(products::add);
		
		return products;
	}
	
	@Override
	public Article findById(long id) {
		return articleRepository.findById(id);
	}
	
	@Override
	public Article findByName(String name) {
		return articleRepository.findByName(name);
	}
	
	@Override
	public void save(@Valid Article product) {
		
		articleRepository.save(product);
		
	}

}
