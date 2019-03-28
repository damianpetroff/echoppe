package ch.hearc.spring.echoppe.data;

import java.util.List;

import javax.validation.Valid;

import ch.hearc.spring.echoppe.model.Command;

public interface CommandDAO {

	List<Command> findAll();
	
	Command findById(long id);

	void save(@Valid Command command);

}
