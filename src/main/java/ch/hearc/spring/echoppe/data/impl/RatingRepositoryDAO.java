package ch.hearc.spring.echoppe.data.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hearc.spring.echoppe.data.RatingDAO;
import ch.hearc.spring.echoppe.model.Rating;
import ch.hearc.spring.echoppe.repository.RatingRepository;

@Component
public class RatingRepositoryDAO implements RatingDAO{

	@Autowired
	private RatingRepository prepo;
	
	@Override
	public List<Rating> findAll() {
		
		List<Rating> ratings = new ArrayList<>();
		
		prepo.findAll().forEach(ratings::add);
		
		return ratings;
	}
	
	@Override
	public Rating findById(long id) {
		return prepo.findById(id);
	}

	@Override
	public void save(@Valid Rating rating) {
		
		prepo.save(rating);
		
	}

}
