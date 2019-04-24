package ch.hearc.spring.echoppe.controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import ch.hearc.spring.echoppe.model.Article;
import ch.hearc.spring.echoppe.model.ArticleCommand;
import ch.hearc.spring.echoppe.model.Command;
import ch.hearc.spring.echoppe.model.Comment;
import ch.hearc.spring.echoppe.model.Payment;
import ch.hearc.spring.echoppe.model.Rating;
import ch.hearc.spring.echoppe.model.Utilisateur;
import ch.hearc.spring.echoppe.repository.ArticleCommandRepository;
import ch.hearc.spring.echoppe.repository.ArticleRepository;
import ch.hearc.spring.echoppe.repository.CategoryRepository;
import ch.hearc.spring.echoppe.repository.CommandRepository;
import ch.hearc.spring.echoppe.repository.CommentRepository;
import ch.hearc.spring.echoppe.repository.PaymentRepository;
import ch.hearc.spring.echoppe.repository.RatingRepository;
import ch.hearc.spring.echoppe.repository.UserRepository;

@Controller
public class ArticleController {
	private static final String NAME = "name";
	private static final String PRICE = "price";
	private static final String CATEGORY = "category";
	private static final String ARTICLES = "articles";
	private static final String ARTICLE = "article";
	private static final String COMMAND = "command";
	
	private static final String ARTICLES_ROUTE = "articles";
	private static final String ARTICLE_ROUTE = "article";

	@Autowired
	private ArticleRepository arepo;

	@Autowired
	private CategoryRepository catrepo;

	@Autowired
	private CommentRepository comrepo;

	@Autowired
	private RatingRepository raterepo;

	@Autowired
	private CommandRepository crepo;

	@Autowired
	private PaymentRepository prepo;

	@Autowired
	private UserRepository urepo;

	@Autowired
	private ArticleCommandRepository acrepo;

	@GetMapping(value = "/articles/{sort}/{order}/{page}")
	public String findAllarticlesPage(@PathVariable("sort") String sort, @PathVariable("order") String order,
			@PathVariable("page") int pageNum, Map<String, Object> model) {

		Sort sortBy = getSortByAndOrder(sort, order);
		
		Pageable page = PageRequest.of(pageNum - 1, 10, sortBy);

		Page<Article> articles = arepo.findAll(page);
		model.put(ARTICLES, articles);
		model.put("pageNum", page.getPageNumber() + 1);
		model.put("pageNb", articles.getTotalPages());
		model.put("sort", sort);
		model.put("order", order);
		model.put("search", "0");

		return ARTICLES_ROUTE;
	}

	@GetMapping(value = "/article/{id}")
	public String findArticle(@PathVariable("id") long id, Model model) {
		model.addAttribute(ARTICLE, arepo.findById(id));

		List<Comment> lcom = comrepo.findAllByArticleId(id);
		List<Rating> lrat = raterepo.findAllByArticleId(id);
		int sumRating = 0;
		int nRating = 0;

		Map<Comment, Rating> map = new HashMap<>();

		for (Rating rate : lrat) {
			for (Comment com : lcom) {
				if (rate.getUtilisateur().equals(com.getUtilisateur())) {
					map.put(com, rate);
					nRating++;
					sumRating += rate.getRating();
				}
			}
		}

		if (nRating == 0) {
			nRating = 1;
		}
		model.addAttribute("averageRating", sumRating / (float) nRating);
		model.addAttribute("comments", map);
		return ARTICLE_ROUTE;
	}

	@GetMapping(value = "/advancedSearch")
	public String advancedSearchPage() {
		return "advancedSearch";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/input_articles")
	public String inputArticles(Map<String, Object> model) {
		model.put("article", new Article());
		model.put("categories", catrepo.findAll());

		return "input_articles";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/alter_article/{id}")
	public String alterArticles(Map<String, Object> model, @PathVariable("id") long id) {
		model.put("categories", catrepo.findAll());
		model.put("article", arepo.findById(id));

		return "alter_article";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/delete_article/{id}")
	public String deleteArticle(@PathVariable("id") long id) {
		arepo.delete(arepo.findById(id));

		return "redirect:/articles/name/asc/1";
	}

	@PostMapping("/articles")
	public String savearticles(Article article, BindingResult errors, Model model) {
		if (!errors.hasErrors()) {
			arepo.save(article);
		}
		return ((errors.hasErrors()) ? "input_articles" : "redirect:/articles/name/asc/1");
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PostMapping("/comment")
	public String commentArticles(HttpServletRequest request, Model model) {
		Long articleId = Long.parseLong(request.getParameter("articleId"));

		Utilisateur u = urepo.findByNomUtilisateur(request.getUserPrincipal().getName());

		List<Comment> lcom = comrepo.findAllByArticleId(articleId);

		for (Comment com : lcom) {
			if (com.getUtilisateur() == u) {
				return "redirect:/article/" + articleId;
			}
		}

		int rating = Integer.parseInt(request.getParameter("rating"));
		String comment = request.getParameter("comment");

		Optional<Article> articleOpt = arepo.findById(articleId);

		if (articleOpt.isPresent()) {
			Article a = articleOpt.get();
			Rating r = new Rating();
			r.setArticle(a);
			r.setRating(rating);
			r.setUtilisateur(u);
			raterepo.save(r);

			Comment c = new Comment();
			c.setArticle(a);

			StringBuilder str = new StringBuilder(comment);
			for (int i = 0; i < new Random().nextInt() % 10; i++) {
				str.append(" ");
			}
			c.setComment(str.toString());
			c.setUtilisateur(u);
			comrepo.save(c);
		}
		return "redirect:/article/" + articleId;

	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PostMapping("/command")
	public String payCommand(HttpServletRequest request, Model model) {
		Long commandId = Long.parseLong(request.getParameter("commandId"));

		Optional<Command> commandOpt = crepo.findById(commandId);
		if (commandOpt.isPresent()) {
			Command command = commandOpt.get();

			Principal principal = request.getUserPrincipal();

			String currentUserName = principal.getName();

			String commandUserName = command.getUtilisateur().getUsername();

			// Workaround, otherwise currentUserName==commandUserName didn't work
			if (commandUserName.compareTo(currentUserName) == 0) {

				Payment payment = new Payment(1, new Date(), Integer.parseInt(request.getParameter("method")));

				prepo.save(payment);
				command.setPayment(payment);
				crepo.save(command);

				model.addAttribute(COMMAND, command);
				return "payment";
			}
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La commande spécifiée ne vous concerne pas");
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PostMapping("/buy")
	public String addArticleToCommand(HttpServletRequest request, Model model) {

		Principal principal = request.getUserPrincipal();

		String currentUserName = principal.getName();

		Utilisateur utilisateur = urepo.findByNomUtilisateur(currentUserName);

		List<Command> userCommands = crepo.findAllByUtilisateur(utilisateur);

		userCommands = userCommands.stream().filter(c -> c.getPayment() == null).collect(Collectors.toList());

		Command newestCommand = null;

		if (userCommands.isEmpty()) {
			newestCommand = new Command();
			newestCommand.setUtilisateur(utilisateur);
			newestCommand.setDate(new Date());
		} else {
			userCommands.sort((c1, c2) -> c1.getDate().compareTo(c2.getDate()));

			newestCommand = userCommands.get(userCommands.size() - 1);
		}

		int quantity = Integer.parseInt(request.getParameter("quantity"));

		long articleId = Long.parseLong(request.getParameter("articleId"));

		Article article = arepo.findById(articleId);

		ArticleCommand articleCommand = new ArticleCommand();

		articleCommand.setArticle(article);
		articleCommand.setQuantity(quantity);

		acrepo.save(articleCommand);

		newestCommand.addContent(articleCommand);

		crepo.save(newestCommand);

		model.addAttribute("command", newestCommand);

		return "redirect:command/" + newestCommand.getId();

	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/command/{id}")
	public String findPayment(@PathVariable("id") long id, Model model, HttpServletRequest request) {

		Command command = crepo.findById(id);

		if (command == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La commande spécifiée n'a pas été trouvée");

		Principal principal = request.getUserPrincipal();

		String currentUserName = principal.getName();

		String commandUserName = command.getUtilisateur().getUsername();

		// Workaround, otherwise currentUserName==commandUserName didn't work
		if (commandUserName.compareTo(currentUserName) == 0) {

			if (command.getPayment() == null) {
				model.addAttribute("command", command);
				return "command";
			} else {
				model.addAttribute("command", command);
				return "payment";
			}
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La commande spécifiée ne vous concerne pas");
		}

	}

	@GetMapping("/search/{what}/{query}/{sort}/{order}/{page}")
	public String searchArticles(@PathVariable("what") String what, @PathVariable("query") String query,
			@PathVariable("sort") String sort, @PathVariable("order") String order, @PathVariable("page") int pageNum,
			HttpServletRequest request, Map<String, Object> model) {

		Sort sortBy = getSortByAndOrder(sort, order);
		
		Pageable page = PageRequest.of(pageNum - 1, 10, sortBy);

		Page<Article> articles = null;

		switch (what) {
		case NAME:
			articles = arepo.findAllByNameIgnoreCaseContaining(query, page);
			break;
		case CATEGORY:
			articles = arepo.findAllByCategoryNameIgnoreCaseContaining(query, page);
			break;
		case "priceLess":
			articles = arepo.findAllByPriceLessThan(Double.valueOf(query), page);
			break;
		case "priceGreater":
			articles = arepo.findAllByPriceGreaterThan(Double.valueOf(query), page);
			break;
		default:
			articles = arepo.findAllByNameIgnoreCaseContaining(query, page);
			break;
		}

		model.put(ARTICLES, articles.getContent());
		model.put("pageNum", page.getPageNumber() + 1);
		model.put("pageNb", articles.getTotalPages());
		model.put("search", "1");
		model.put("searchQuery", query);
		model.put("what", what);
		model.put("sort", sort);
		model.put("order", order);
		return ARTICLES_ROUTE;
	}

	private Sort getSortByAndOrder(String sort, String order) {
		Sort sortBy = null;
		
		try {
			switch (sort) {
			case NAME:
				sortBy = Sort.by(PRICE);
				break;
			case PRICE:
				sortBy = Sort.by(PRICE);
				break;
			case CATEGORY:
				sortBy = Sort.by("category.name");
				break;
			default:
				sortBy = Sort.by(NAME);
				break;
			}
		
			switch (order) {
			case "asc":
				sortBy = sortBy.ascending();
				break;
			case "desc":
				sortBy = sortBy.descending();
				break;
			default:
				sortBy = sortBy.ascending();
				break;
			}
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		return sortBy;
	}
}
