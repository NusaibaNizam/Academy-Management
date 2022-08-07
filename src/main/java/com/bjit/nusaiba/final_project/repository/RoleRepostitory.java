package com.bjit.nusaiba.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjit.nusaiba.final_project.entity.Role;

@Repository
public interface RoleRepostitory extends JpaRepository<Role, Integer> {
	Role findByName(String name);
}
