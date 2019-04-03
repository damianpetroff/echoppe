package ch.hearc.spring.echoppe.data;

import java.util.List;

import javax.validation.Valid;

import ch.hearc.spring.echoppe.model.Rating;

public interface RatingDAO {

	List<Rating> findAll();
	
	Rating findById(long id);

	void save(@Valid Rating product);

}
