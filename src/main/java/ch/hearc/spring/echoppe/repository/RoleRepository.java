package ch.hearc.spring.echoppe.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.spring.echoppe.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByNom(String name);
}