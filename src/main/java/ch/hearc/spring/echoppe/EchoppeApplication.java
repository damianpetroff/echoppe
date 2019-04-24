package ch.hearc.spring.echoppe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ch.hearc.spring.echoppe.model.Article;
import ch.hearc.spring.echoppe.model.ArticleCommand;
import ch.hearc.spring.echoppe.model.Category;
import ch.hearc.spring.echoppe.model.Command;
import ch.hearc.spring.echoppe.model.Comment;
import ch.hearc.spring.echoppe.model.Payment;
import ch.hearc.spring.echoppe.model.Rating;
import ch.hearc.spring.echoppe.model.Role;
import ch.hearc.spring.echoppe.model.Utilisateur;
import ch.hearc.spring.echoppe.repository.ArticleCommandRepository;
import ch.hearc.spring.echoppe.repository.ArticleRepository;
import ch.hearc.spring.echoppe.repository.CategoryRepository;
import ch.hearc.spring.echoppe.repository.CommandRepository;
import ch.hearc.spring.echoppe.repository.CommentRepository;
import ch.hearc.spring.echoppe.repository.PaymentRepository;
import ch.hearc.spring.echoppe.repository.RatingRepository;
import ch.hearc.spring.echoppe.repository.RoleRepository;
import ch.hearc.spring.echoppe.repository.UserRepository;

@SpringBootApplication
public class EchoppeApplication {
	
	public static Random rnd = new Random();

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
	private CategoryRepository categoryRepo;

	@Autowired
	private ArticleRepository articleRepo;

	@Autowired
	private RatingRepository ratingRepo;

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private CommandRepository commandRepo;

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private ArticleCommandRepository articleCommandRepo;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@PostConstruct
	public void initData() {
		// Role
		Role roleAdmin = new Role("ROLE_ADMIN");
		Role roleUser = new Role("ROLE_USER");
		roleRepo.save(roleAdmin);
		roleRepo.save(roleUser);

		// User
		Utilisateur admin = new Utilisateur("admin", "admin@test.ch", bCryptPasswordEncoder.encode("password"));
		Utilisateur user = new Utilisateur("user", "user@test.ch", bCryptPasswordEncoder.encode("password"));

		HashSet<Role> adminRoles = new HashSet<Role>();
		adminRoles.add(roleAdmin);
		adminRoles.add(roleUser);

		admin.addRoles(adminRoles);
		user.addRole(roleUser);

		utilisateurRepo.save(admin);
		utilisateurRepo.save(user);

		// Category
		Category c1 = new Category("Nourriture");
		Category c2 = new Category("Boisson");
		Category c3 = new Category("Habit");
		Category c4 = new Category("Gun");
		Category c5 = new Category("Outil");
		categoryRepo.save(c1);
		categoryRepo.save(c2);
		categoryRepo.save(c3);
		categoryRepo.save(c4);
		categoryRepo.save(c5);

		List<Category> categoryList = new ArrayList<Category>();

		categoryList.add(c1);
		categoryList.add(c2);
		categoryList.add(c3);
		categoryList.add(c4);
		categoryList.add(c5);

		// Article
		Article article1 = new Article("Banane", 2.4);
		Article article2 = new Article("Crayon", 0.5);
		article1.setCategory(c1);
		article2.setCategory(c5);
		articleRepo.save(article1);
		articleRepo.save(article2);

		// Rating
		Rating rating1 = new Rating(5, user, article1);
		Rating rating2 = new Rating(2, user, article2);
		ratingRepo.save(rating1);
		ratingRepo.save(rating2);

		// Comment
		Comment comment1 = new Comment("C'est plutôt pas mal", user, article1);
		Comment comment2 = new Comment("Vraiment nul !", user, article2);
		commentRepo.save(comment1);
		commentRepo.save(comment2);

		// Command
		Command command1 = new Command();
		Command command2 = new Command();

		// Payment
		Payment payment1 = null;
		Payment payment2 = null;
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			payment1 = new Payment(1, sdf.parse("04-04-2019 10:30:00"), 0);
			payment2 = new Payment(1, sdf.parse("04-04-2019 10:30:00"), 0);
			paymentRepo.save(payment1);
			paymentRepo.save(payment2);

			command1.setDate(sdf.parse("04-04-2019 10:30:00"));
			command2.setDate(sdf.parse("04-04-2019 10:29:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ArticleCommand ac1 = new ArticleCommand(article1, 5);
		ArticleCommand ac2 = new ArticleCommand(article2, 5);
		ArticleCommand ac3 = new ArticleCommand(article1, 3);
		ArticleCommand ac4 = new ArticleCommand(article2, 7);

		articleCommandRepo.save(ac1);
		articleCommandRepo.save(ac2);
		articleCommandRepo.save(ac3);
		articleCommandRepo.save(ac4);

		command1.addContent(ac1);
		command1.addContent(ac2);
		command2.addContent(ac3);
		command2.addContent(ac4);

		// command1.setPayment(payment1);
		command2.setPayment(payment2);

		command1.setUtilisateur(user);
		command2.setUtilisateur(user);

		commandRepo.save(command1);
		commandRepo.save(command2);

		if (articleRepo.count() < 10) {



			// Adding articles with random name and price to test pagination
			for (int i = 0; i < 100; i++) {
				Article a = new Article(randomAlphaNumeric(10), rnd.nextDouble() * 100);
				a.setCategory(categoryList.get(rnd.nextInt(categoryList.size())));
				articleRepo.save(a);
			}
		
		}

	}

	// source: https://dzone.com/articles/generate-random-alpha-numeric
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String randomAlphaNumeric(int count) {
		count--;
		StringBuilder builder = new StringBuilder();
		int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());

		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		while (count-- != 0) {
			character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.toLowerCase().charAt(character));
		}
		return builder.toString();
	}

}
