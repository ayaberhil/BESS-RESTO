package com.projet.projet.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.projet.projet.beans.User;

public interface UserService extends UserDetailsService {
	User save(User user);
	User getByMail(String mail);


}