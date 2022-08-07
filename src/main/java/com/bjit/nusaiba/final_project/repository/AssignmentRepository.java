package com.bjit.nusaiba.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjit.nusaiba.final_project.entity.Assignment;
import com.bjit.nusaiba.final_project.entity.Batch;
import com.bjit.nusaiba.final_project.entity.Trainer;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
	Iterable<Assignment> findByNameContainingIgnoreCase(String name);
	Iterable<Assignment> findByTrainer(Trainer trainer);
	Iterable<Assignment> findByBatch(Batch batch);
}
