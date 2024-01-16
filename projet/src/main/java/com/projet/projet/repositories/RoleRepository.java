package com.projet.projet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.projet.beans.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
    Role findByNom(String role);


}
