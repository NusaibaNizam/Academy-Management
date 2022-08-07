package com.bjit.nusaiba.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjit.nusaiba.final_project.entity.Course;
import com.bjit.nusaiba.final_project.entity.Trainer;
import com.bjit.nusaiba.final_project.entity.TrainingSession;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Integer> {
	Iterable<TrainingSession> findByNameContainingIgnoreCase(String name);
	Iterable<TrainingSession> findByCourseAndWeekDay(Course course,Integer weekDay);
	Iterable<TrainingSession> findByCourse(Course course);
	Iterable<TrainingSession> findDistinctByCourse_TrainerAndWeekDay(Trainer trainer,Integer weekDay);
	Iterable<TrainingSession> findDistinctByCourse_Trainer(Trainer trainer);
	TrainingSession findByName(String name);
}
