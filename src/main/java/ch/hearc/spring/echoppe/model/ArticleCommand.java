package ch.hearc.spring.echoppe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ArticleCommand {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@ManyToOne
	@JoinColumn
	private Article article;

	@ManyToOne
	@JoinColumn
	private Command command;

	private int quantity;

	private float price;

	public ArticleCommand(Long id, Article article, Command command, int quantity, float price) {
		super();
		this.id = id;
		this.article = article;
		this.command = command;
		this.quantity = quantity;
		this.price = price;
	}

	@Override
	public String toString() {
		return "ArticleCommand [id=" + id + ", article=" + article + ", command=" + command + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
