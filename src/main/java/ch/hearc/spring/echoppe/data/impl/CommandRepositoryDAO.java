package ch.hearc.spring.echoppe.data.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hearc.spring.echoppe.data.CommandDAO;
import ch.hearc.spring.echoppe.model.Command;
import ch.hearc.spring.echoppe.repository.CommandRepository;

@Component
public class CommandRepositoryDAO implements CommandDAO{

	@Autowired
	private CommandRepository commandRepository;
	
	@Override
	public List<Command> findAll() {
		
		List<Command> commands = new ArrayList<>();
		
		commandRepository.findAll().forEach(commands::add);
		
		return commands;
	}
	
	@Override
	public Command findById(long id) {
		return commandRepository.findById(id);
	}

	@Override
	public void save(@Valid Command command) {
		
		commandRepository.save(command);
		
	}

}
