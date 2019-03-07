package ch.hearc.spring.echoppe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.spring.echoppe.data.ProductDAO;
import ch.hearc.spring.echoppe.repository.ProductRepository;



@Controller
public class HomeController {

		@Autowired
		ProductDAO productDao;
		
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
//			model.put("products", productDao.findAll());
//			return "basic";
//		}
		
		
		
}


