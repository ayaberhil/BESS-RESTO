package com.projet.projet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.projet.beans.User;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByMail(String mail);

}
