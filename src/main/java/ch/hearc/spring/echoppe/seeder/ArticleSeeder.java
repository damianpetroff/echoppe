package ch.hearc.spring.echoppe.seeder;

import org.springframework.beans.factory.annotation.Autowired;

import ch.hearc.spring.echoppe.model.Article;
import ch.hearc.spring.echoppe.repository.ArticleRepository;

public class ArticleSeeder {

	private ArticleRepository articleRepo;

	@Autowired
	public ArticleSeeder() {

	}

	public void seed() {
		// TODO: add categories
		Article a = new Article();
		a.setName("Banane");
		a.setPrice(2.4);

		articleRepo.save(a);

		Article b = new Article();
		b.setName("Crayon");
		b.setPrice(0.5);

		articleRepo.save(b);
	}
}
