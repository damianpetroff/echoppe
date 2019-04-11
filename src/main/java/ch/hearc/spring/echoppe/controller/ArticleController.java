package ch.hearc.spring.echoppe.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.spring.echoppe.model.Article;

@Controller
public class ArticleController {

	@Autowired
	private ArticleRepository arepo;

	@GetMapping(value = "/articles")
	public String findAllarticles(Map<String, Object> model) {
		System.out.println("/articles GET");
		model.put("articles", arepo.findAll());
		model.put("article", new Article());

		return "articles";
	}

	@GetMapping(value = "/article/{id}")
	public String findArticle(@PathVariable("id") long id, Model model) {
		model.addAttribute("article", arepo.findById(id));
		return "article";
	}

	@GetMapping(value = "/input_articles")
	public String inputArticles(Map<String, Object> model) {

		model.put("article", new Article());

		return "input_articles";
	}

	@PostMapping("/articles")
	public String savearticles(Article article, BindingResult errors, Model model) {

		if (!errors.hasErrors()) {
			arepo.save(article);
        	}
        	return ((errors.hasErrors()) ? "input_articles" : "redirect:articles");
	}

	@GetMapping(value = "/payment")
	public String payCommand(Map<String, Object> model){
		model.put("articles", arepo.findAll());

		model.put("article", new Article());

		return "payment";
	}
}
