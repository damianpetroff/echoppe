package ch.hearc.spring.echoppe.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import ch.hearc.spring.echoppe.model.Payment;
import ch.hearc.spring.echoppe.model.Utilisateur;
import ch.hearc.spring.echoppe.repository.ArticleCommandRepository;
import ch.hearc.spring.echoppe.repository.ArticleRepository;
import ch.hearc.spring.echoppe.repository.CommandRepository;
import ch.hearc.spring.echoppe.repository.PaymentRepository;
import ch.hearc.spring.echoppe.repository.UserRepository;

@Controller
public class ArticleController {

	@Autowired
	private ArticleRepository arepo;

	@Autowired
	private CommandRepository crepo;

	@Autowired
	private PaymentRepository prepo;

	@Autowired
	private UserRepository urepo;

	@Autowired
	private ArticleCommandRepository acrepo;


	@GetMapping(value = "/articles/{page}")
	public String findAllarticlesPage(@PathVariable("page") int pageNum, Map<String, Object> model) {

		Pageable page = PageRequest.of(pageNum - 1, 10);
		Page<Article> articles = arepo.findAll(page);
		model.put("articles", articles);
		model.put("pageNum", page.getPageNumber() + 1);
		model.put("pageNb", articles.getTotalPages());
		model.put("search", "0");

		return "articles";
	}

	@GetMapping(value = "/article/{id}")
	public String findArticle(@PathVariable("id") long id, Model model) {
		model.addAttribute("article", arepo.findById(id));
		return "article";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
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

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PostMapping("/command")
	public String payCommand(HttpServletRequest request, Model model) {
		Long commandId = Long.parseLong(request.getParameter("commandId"));

		Optional<Command> commandOpt = crepo.findById(commandId);
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

			model.addAttribute("command", command);
			return "payment";
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La commande spécifiée ne vous concerne pas");
		}

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

		int quantity = Integer.valueOf(request.getParameter("quantity"));

		long articleId = Long.valueOf(request.getParameter("articleId"));

		Article article = arepo.findById(articleId);

		ArticleCommand articleCommand = new ArticleCommand();

		articleCommand.setArticle(article);
		articleCommand.setQuantity(quantity);

		acrepo.save(articleCommand);

		newestCommand.addContent(articleCommand);

		crepo.save(newestCommand);

		model.addAttribute("command", newestCommand);
		return "command";

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

	@GetMapping("/search/{query}/{page}")
	public String searchArticles(@PathVariable("query") String query, @PathVariable("page") int pageNum,
			HttpServletRequest request, Map<String, Object> model) {

		Pageable page = PageRequest.of(pageNum - 1, 10);
		Page<Article> articles = arepo.findAllByNameIgnoreCaseContaining(query, page);

		model.put("articles", articles.getContent());
		model.put("pageNum", page.getPageNumber() + 1);
		model.put("pageNb", articles.getTotalPages());
		model.put("search", "1");
		model.put("searchQuery", query);

		return "articles";
	}
}
