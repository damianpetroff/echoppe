package ch.hearc.spring.echoppe.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.hearc.spring.echoppe.data.ArticleDAO;

@Controller
public class HomeController {

	@Autowired
	ArticleDAO articleDao;

	@Value("${accueil.message:test}")
	private String message;

	@Value("${accueil1.message:[default]}")
	private String messageParDefaut;

	@GetMapping("/")
	public String home(Map<String, Object> model) {
		model.put("page", "Home");
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
