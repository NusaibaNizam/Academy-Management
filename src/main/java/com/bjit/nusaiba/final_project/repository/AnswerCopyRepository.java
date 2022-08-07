package com.bjit.nusaiba.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjit.nusaiba.final_project.entity.Assignment;
import com.bjit.nusaiba.final_project.entity.AnswerCopy;
import com.bjit.nusaiba.final_project.entity.Student;
import com.bjit.nusaiba.final_project.entity.Trainer;

public interface AnswerCopyRepository extends JpaRepository<AnswerCopy, Integer> {
	Iterable<AnswerCopy> findByAssignment(Assignment assignment);
	Iterable<AnswerCopy> findByStudent(Student student);
	Iterable<AnswerCopy> findBySubmmited(boolean submmited);	
	Iterable<AnswerCopy> findByStudentAndSubmmited(Student student,boolean submmited);
	Iterable<AnswerCopy> findByAssignment_TrainerAndSubmmited(Trainer trainer, boolean submmited);
	
}
