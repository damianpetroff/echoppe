package ch.hearc.spring.echoppe.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.hearc.spring.echoppe.model.Article;
import ch.hearc.spring.echoppe.repository.ArticleRepository;

@Controller
public class HomeController {
	
	@Autowired
	private ArticleRepository arepo;
	
	@Value("${accueil.message:test}")
	private String message;

	@Value("${accueil1.message:[default]}")
	private String messageParDefaut;

	@GetMapping("/")
	public String home(Map<String, Object> model) {
		model.put("page", "Home");
		Random rand = new Random();
	    List<Article> articles = arepo.findAll();
	    List<Article> randomArticles = new ArrayList();
	 
	    int numberOfElements = 4;
	 
	    for (int i = 0; i < numberOfElements; i++) {
	        int randomIndex = rand.nextInt(articles.size());
	        randomArticles.add(articles.get(randomIndex));
	    }
	    
	    model.put("randomArticles", randomArticles);
	    	    
		return "home";
	}

//		@GetMapping("/basic")
//		public String basic(Map<String, Object> model) {
//			model.put("message", this.message);
//			model.put("page", "Concepts de bases");
//			
//			model.put("message", this.message);
//			model.put("messageParDefaut", this.messageParDefaut);
//			model.put("page", "Concepts de bases");
//			
//			
//			model.put("articles", articleDao.findAll());
//			return "basic";
//		}

}
