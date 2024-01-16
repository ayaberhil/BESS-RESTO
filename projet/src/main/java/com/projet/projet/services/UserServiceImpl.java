package com.projet.projet.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet.projet.beans.Role;
import com.projet.projet.beans.User;
import com.projet.projet.repositories.RoleRepository;
import com.projet.projet.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

	@Override
	public User save(User user) {
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.findByNom("PROPRIETAIRE"));
		User userAccount = new User(user.getMail(),user.getPrenom(),user.getNom(),passwordEncoder.encode(user.getPassword()),roles);
		
		return userRepository.save(userAccount);
	}

	@Override
	public User getByMail(String mail) {
		User user = userRepository.findByMail(mail);
		return user;
	}
		
	
}