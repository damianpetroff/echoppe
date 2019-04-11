package ch.hearc.spring.echoppe.data.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hearc.spring.echoppe.data.CommentDAO;
import ch.hearc.spring.echoppe.model.Comment;
import ch.hearc.spring.echoppe.repository.CommentRepository;

@Component
public class CommentRepositoryDAO implements CommentDAO {

	@Autowired
	private CommentRepository prepo;

	@Override
	public List<Comment> findAll() {

		List<Comment> comments = new ArrayList<>();

		prepo.findAll().forEach(comments::add);

		return comments;
	}

	@Override
	public Comment findById(long id) {
		return prepo.findById(id);
	}

	@Override
	public void save(@Valid Comment comment) {

		prepo.save(comment);

	}

}
