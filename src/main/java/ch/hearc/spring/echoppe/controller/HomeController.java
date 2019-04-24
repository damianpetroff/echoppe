package ch.hearc.spring.echoppe.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.spring.echoppe.model.Article;
import ch.hearc.spring.echoppe.model.Role;
import ch.hearc.spring.echoppe.model.Utilisateur;
import ch.hearc.spring.echoppe.repository.ArticleRepository;
import ch.hearc.spring.echoppe.repository.CommandRepository;
import ch.hearc.spring.echoppe.repository.RoleRepository;
import ch.hearc.spring.echoppe.repository.UserRepository;

@Controller
public class HomeController {

	@Autowired
	private UserRepository urepo;

	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private CommandRepository crepo;
	
	@Autowired
	private ArticleRepository arepo;

	@GetMapping("/")
	public String home(Map<String, Object> model) {
		model.put("page", "Home");
		Random rand = new Random();
		List<Article> articles = (List<Article>) arepo.findAll();
		List<Article> randomArticles = new ArrayList();

		int numberOfElements = 4;

		for (int i = 0; i < numberOfElements; i++) {
			int randomIndex = rand.nextInt(articles.size());
			randomArticles.add(articles.get(randomIndex));
		}

		model.put("randomArticles", randomArticles);

		return "home";
	}
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/profil")
	public String profil(HttpServletRequest request,Map<String, Object> model) {
		Principal principal = request.getUserPrincipal();

		String currentUserName = principal.getName();

		Utilisateur utilisateur = urepo.findByNomUtilisateur(currentUserName);
		
		model.put("user",utilisateur);
		model.put("commands", crepo.findAllByUtilisateurOrderByDateDesc(utilisateur));
		return "profile";
	}


	@PostMapping("/register")
	public String register(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (urepo.findByNomUtilisateur(username) == null) {
			Utilisateur user = new Utilisateur();
			user.setEmail(email);
			user.setUsername(username);
			user.setMotDePasse(new BCryptPasswordEncoder().encode(password));

			Role role = roleRepo.findByNom("ROLE_USER");

			Set<Role> roles = new HashSet<Role>();
			roles.add(role);
			user.setRoles(roles);
			urepo.save(user);

			try {
				request.login(username, password);
			} catch (ServletException e) {
				e.printStackTrace();
			}

		}

		return "redirect:/";
	}

	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}

}
