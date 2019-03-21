package ch.hearc.spring.echoppe.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.spring.echoppe.model.Utilisateur;

public interface UserRepository extends CrudRepository<Utilisateur, Long> {
	Utilisateur findByNomUtilisateur(String username);
}