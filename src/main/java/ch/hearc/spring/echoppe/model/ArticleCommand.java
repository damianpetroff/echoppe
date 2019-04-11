package ch.hearc.spring.echoppe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ArticleCommand {

	// Attributes
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

	// Constructor
	public ArticleCommand(Article article, Command command, int quantity, float price) {
		super();
		this.article = article;
		this.command = command;
		this.quantity = quantity;
		this.price = price;
	}

	// Getters
	public Long getId() {
		return id;
	}

	public Article getArticle() {
		return article;
	}

	public Command getCommand() {
		return command;
	}

	public int getQuantity() {
		return quantity;
	}

	public float getPrice() {
		return price;
	}

	// Setters
	public void setArticle(Article article) {
		this.article = article;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	// ToString
	@Override
	public String toString() {
		return "ArticleCommand [id=" + id + ", article=" + article + ", command=" + command + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleCommand other = (ArticleCommand) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (command == null) {
			if (other.command != null)
				return false;
		} else if (!command.equals(other.command))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

}
