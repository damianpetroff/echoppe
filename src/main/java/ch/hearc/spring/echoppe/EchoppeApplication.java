package ch.hearc.spring.echoppe;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ch.hearc.spring.echoppe.model.Command;
import ch.hearc.spring.echoppe.model.Payment;
import ch.hearc.spring.echoppe.model.Article;
import ch.hearc.spring.echoppe.model.ArticleCommand;
import ch.hearc.spring.echoppe.model.Comment;
import ch.hearc.spring.echoppe.model.Rating;
import ch.hearc.spring.echoppe.model.Role;
import ch.hearc.spring.echoppe.model.Utilisateur;
import ch.hearc.spring.echoppe.repository.ArticleRepository;
import ch.hearc.spring.echoppe.repository.CommentRepository;
import ch.hearc.spring.echoppe.repository.RatingRepository;
import ch.hearc.spring.echoppe.repository.RoleRepository;
import ch.hearc.spring.echoppe.repository.UserRepository;

@SpringBootApplication
public class EchoppeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EchoppeApplication.class, args);
	}

	@Autowired
	private UserRepository utilisateurRepo;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private ArticleRepository articleRepo;

	@Autowired
	private RatingRepository ratingRepo;

	@Autowired
	private CommentRepository commentRepo;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@PostConstruct
	public void initData() {
		Role roleAdmin = new Role();
		roleAdmin.setNom("ROLE_ADMIN");
		roleRepo.save(roleAdmin);

		Role roleUser = new Role();
		roleUser.setNom("ROLE_USER");
		roleRepo.save(roleUser);

		Utilisateur admin = new Utilisateur();
		admin.setUsername("admin");
		admin.setMotDePasse(bCryptPasswordEncoder.encode("password"));

		Set<Role> roles = new HashSet<>();
		roles.add(roleAdmin);
		admin.setRoles(roles);

		utilisateurRepo.save(admin);

		Utilisateur user = new Utilisateur();
		user.setUsername("user");
		user.setMotDePasse(bCryptPasswordEncoder.encode("password"));

		Set<Role> rolesUser = new HashSet<>();
		rolesUser.add(roleUser);
		user.setRoles(rolesUser);

		utilisateurRepo.save(user);
	
		// new ArticleSeeder().seed();

		
		//TODO: add categories to articles
		
		// Article
		Article article1 = new Article();
		article1.setName("Banane");
		article1.setPrice(2.4);

		articleRepo.save(article1);

		Article article2 = new Article();
		article2.setName("Crayon");
		article2.setPrice(0.5);

		articleRepo.save(article2);

		// Rating
		Rating rating1 = new Rating();
		rating1.setArticle(article1);
		rating1.setRating(5);
		rating1.setUtilisateur(user);

		ratingRepo.save(rating1);

		Rating rating2 = new Rating();
		rating2.setArticle(article2);
		rating2.setRating(2);
		rating2.setUtilisateur(user);

		ratingRepo.save(rating2);
		
		// Comment
		Comment comment1 = new Comment();
		comment1.setArticle(article1);
		comment1.setUtilisateur(user);
		comment1.setComment("C'est plutôt pas mal");

		commentRepo.save(comment1);

		Comment comment2 = new Comment();
		comment2.setArticle(article2);
		comment2.setUtilisateur(user);
		comment2.setComment("Vraiment nul !");

		commentRepo.save(comment2);
		
		// Payment
		Payment payment1 = new Payment(user, 1, new Date(2019,4,4), 0, new BigDecimal(32.20));
		Payment payment2 = new Payment(user, 1, new Date(2019,4,4), 0, new BigDecimal(32.20));
		Payment payment3 = new Payment(user, 1, new Date(2019,4,4), 0, new BigDecimal(32.20));
		
		
		
		// Command
		Command command1 = new Command();
		Command command2 = new Command();
		Command command3 = new Command();
		command1.setPayment(payment1);
	}

}
