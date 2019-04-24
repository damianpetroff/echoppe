package ch.hearc.spring.echoppe.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.spring.echoppe.model.Role;
import ch.hearc.spring.echoppe.model.Utilisateur;
import ch.hearc.spring.echoppe.repository.RoleRepository;
import ch.hearc.spring.echoppe.repository.UserRepository;

@Controller
public class HomeController {

	@Autowired
	private UserRepository urepo;

	@Autowired
	private RoleRepository roleRepo;

	@GetMapping("/")
	public String home(Map<String, Object> model) {
		model.put("page", "Home");
		return "home";
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

		return "home";
	}

	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}

}
