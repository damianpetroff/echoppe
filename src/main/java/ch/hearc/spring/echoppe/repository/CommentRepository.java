package ch.hearc.spring.echoppe.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.spring.echoppe.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	Comment findById(long id);
	List<Comment> findAllByArticleId(long id);
}
