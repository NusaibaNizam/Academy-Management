package com.bjit.nusaiba.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjit.nusaiba.final_project.entity.Course;
import com.bjit.nusaiba.final_project.entity.Trainer;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	Iterable<Course> findByNameContainingIgnoreCase(String name);
	Course findByName(String name);
	Iterable<Course> findByTrainer(Trainer trainer);
}
