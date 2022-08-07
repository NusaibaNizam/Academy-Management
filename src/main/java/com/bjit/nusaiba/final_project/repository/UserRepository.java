package com.bjit.nusaiba.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjit.nusaiba.final_project.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Iterable<User> findByNameContainingIgnoreCase(String name);
	User findByName(String name);
	User findByEmail(String email);
}
