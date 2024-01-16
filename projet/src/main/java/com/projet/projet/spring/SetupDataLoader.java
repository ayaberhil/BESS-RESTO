package com.projet.projet.spring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.projet.projet.beans.Etat;
import com.projet.projet.beans.Role;
import com.projet.projet.beans.User;
import com.projet.projet.enums.EtatEnum;
import com.projet.projet.enums.RoleEnum;
import com.projet.projet.repositories.RoleRepository;
import com.projet.projet.repositories.UserRepository;
import com.projet.projet.repositories.EtatRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>{

	boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private EtatRepository etatRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;
		
		createEtatIfNotFound(EtatEnum.EN_COURS.name());
		createEtatIfNotFound(EtatEnum.VALIDE.name());
		createEtatIfNotFound(EtatEnum.NON_VALIDE.name());
		
		createRoleIfNotFound(RoleEnum.ADMIN.name());
		createRoleIfNotFound(RoleEnum.PROPRIETAIRE.name());

		Role adminRole = roleRepository.findByNom(RoleEnum.ADMIN.name());
		Role ownerRole = roleRepository.findByNom(RoleEnum.PROPRIETAIRE.name());
		
		creatUserIfNotFound("owner@owner", "owner@owner", new Role[]{ownerRole});
		creatUserIfNotFound("admin@admin", "admin@admin", new Role[]{ownerRole,adminRole});
		
		alreadySetup = true;
		System.out.println("launched the app .............");
	}
	
	@Transactional
	void creatUserIfNotFound(String userName, String password,Role[] role) {
		if(userRepository.findByMail(userName) == null) {
			User user = new User();
			user.setPrenom("TEST");
			user.setNom("TEST");
			user.setPassword(passwordEncoder.encode(password));
			user.setMail(userName);
			Set<Role> roles = new HashSet<Role>(Arrays.asList(role));
			user.setRoles(roles);
			userRepository.save(user);
		}
	}

	@Transactional
	Role createRoleIfNotFound(String name) {

		Role role = roleRepository.findByNom(name);
		if (role == null) {
			role = new Role();
			role.setNom(name);
			roleRepository.save(role);
		}
		return role;
	}
	
	@Transactional
	Etat createEtatIfNotFound(String name) {

		Etat etat = etatRepository.findByNom(name);
		if (etat == null) {
			etat = new Etat();
			etat.setNom(name);
			etatRepository.save(etat);
		}
		return etat;
	}
}

