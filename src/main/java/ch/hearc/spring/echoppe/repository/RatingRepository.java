package ch.hearc.spring.echoppe.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.spring.echoppe.model.Rating;

public interface RatingRepository extends CrudRepository<Rating, Long> {
	Rating findById(long id);

	List<Rating> findAllByArticleId(long id);
}
