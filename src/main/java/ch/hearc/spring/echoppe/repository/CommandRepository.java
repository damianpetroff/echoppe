package ch.hearc.spring.echoppe.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.spring.echoppe.model.Command;
import ch.hearc.spring.echoppe.model.Utilisateur;

public interface CommandRepository extends CrudRepository<Command, Long>{
	Command findById(long id);
	List<Command> findAllByUtilisateur(Utilisateur utilisateur);
	List<Command> findAllByUtilisateurOrderByDateDesc(Utilisateur utilisateur);
}
