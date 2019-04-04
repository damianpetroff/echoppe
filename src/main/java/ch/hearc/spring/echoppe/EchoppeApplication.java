package ch.hearc.spring.echoppe;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ch.hearc.spring.echoppe.model.Command;
import ch.hearc.spring.echoppe.model.Payment;
import ch.hearc.spring.echoppe.model.Role;
import ch.hearc.spring.echoppe.model.Utilisateur;
import ch.hearc.spring.echoppe.repository.RoleRepository;
import ch.hearc.spring.echoppe.repository.UserRepository;



@SpringBootApplication
public class EchoppeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EchoppeApplication.class, args);
	}
	
	
	@Autowired
	private UserRepository utilisateurRepo;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Bean 
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@PostConstruct
	public void initData() {
		Role roleAdmin = new Role();
		roleAdmin.setNom("ROLE_ADMIN");
		roleRepo.save(roleAdmin);
		
		Role roleUser=new Role();
		roleUser.setNom("ROLE_USER");
		roleRepo.save(roleUser);
		
		Utilisateur admin = new Utilisateur();
		admin.setUsername("admin");
		admin.setMotDePasse(bCryptPasswordEncoder.encode("password"));
		
		Set<Role> roles = new HashSet<>();
		roles.add(roleAdmin);
		admin.setRoles(roles);
		
		utilisateurRepo.save(admin);
		
		Utilisateur user=new Utilisateur();
		user.setUsername("user");
		user.setMotDePasse(bCryptPasswordEncoder.encode("password"));
		
		Set<Role> rolesUser=new HashSet<>();
		rolesUser.add(roleUser);
		user.setRoles(rolesUser);
		
		utilisateurRepo.save(user);
		
		Payment payment1 = new Payment(user, 1, new Date(2019,4,4), 0, new BigDecimal(32.20));
		Payment payment2 = new Payment(user, 1, new Date(2019,4,4), 0, new BigDecimal(32.20));
		Payment payment3 = new Payment(user, 1, new Date(2019,4,4), 0, new BigDecimal(32.20));
		
		Command command1 = new Command();
		Command command2 = new Command();
		Command command3 = new Command();
		command1.setPayment(payment1);
		
		
	}
	
}
