package ch.hearc.spring.echoppe.data;

import java.util.List;

import javax.validation.Valid;

import ch.hearc.spring.echoppe.model.Comment;

public interface CommentDAO {

	List<Comment> findAll();
	
	Comment findById(long id);

	void save(@Valid Comment product);

}
