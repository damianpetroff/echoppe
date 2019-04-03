package ch.hearc.spring.echoppe.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.spring.echoppe.model.Command;

public interface CommandRepository extends CrudRepository<Command, Long>{
	Command findById(long id);
}
