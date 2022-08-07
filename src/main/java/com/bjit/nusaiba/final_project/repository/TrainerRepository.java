package com.bjit.nusaiba.final_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjit.nusaiba.final_project.entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
	Iterable<Trainer> findByAcceptedAndNameContainingIgnoreCase(Boolean accepted, String name);
	List<Trainer> findByAccepted(Boolean accepted);
	Trainer findByName(String name);
	Trainer findByEmail(String email);
}
