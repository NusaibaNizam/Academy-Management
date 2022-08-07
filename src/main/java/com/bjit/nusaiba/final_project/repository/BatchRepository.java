package com.bjit.nusaiba.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjit.nusaiba.final_project.entity.Batch;
import com.bjit.nusaiba.final_project.entity.Course;
import com.bjit.nusaiba.final_project.entity.Trainer;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {
	Iterable<Batch> findByNameContainingIgnoreCase(String name);
	Iterable<Batch> findDistinctByCourses_Trainer(Trainer trainer);
	Iterable<Batch> findDistinctByCourses(Course course);
	Batch findByName(String name);
}
