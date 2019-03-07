package ch.hearc.spring.echoppe.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.spring.echoppe.data.ProductDAO;
import ch.hearc.spring.echoppe.model.Product;
import ch.hearc.spring.echoppe.repository.ProductRepository;

@Controller
public class ProductController {

	
	@Autowired
	private ProductDAO pdao;
	
	//private ProduitRepository prepo;
	
	@GetMapping(value = "/products")
	public String findAllproducts(Map<String, Object> model){
		System.out.println("/products GET");
		model.put("products", pdao.findAll());
		model.put("product", new Product());
		
		
		return "products";
	}
	
	@GetMapping(value = "/input_products")
	public String inputProducts(Map<String, Object> model){
		
		
		model.put("product", new Product());
		
		
		return "input_products";
	}
	
	@PostMapping("/products")
	public String saveproducts(@Valid @ModelAttribute Product product, BindingResult errors, Model model) {
		
		
		if (!errors.hasErrors()) {
            pdao.save(product);
        }
        return ((errors.hasErrors()) ? "input_products" : "redirect:products");
	}
}
