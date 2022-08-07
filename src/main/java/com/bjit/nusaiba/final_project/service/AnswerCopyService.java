package com.bjit.nusaiba.final_project.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import com.bjit.nusaiba.final_project.entity.AnswerCopy;
import com.bjit.nusaiba.final_project.entity.Assignment;
import com.bjit.nusaiba.final_project.entity.Student;
import com.bjit.nusaiba.final_project.entity.Trainer;

@EnableCaching
public interface AnswerCopyService {
	@CacheEvict
	void saveAnswerCopy(AnswerCopy answerCopy);
	
	@Cacheable
	Iterable<AnswerCopy> getAllAnswerCopies();
	
	@Cacheable
	Iterable<AnswerCopy> getAllAnswerCopiesByStudent(Student student);
	
	@Cacheable
	Iterable<AnswerCopy> getAllAnswerCopiesByAssignment(Assignment assignment);
	
	@Cacheable
	Iterable<AnswerCopy> getAllAnswerCopiesBySubmitted(boolean submitted);
	
	@Cacheable
	Iterable<AnswerCopy> getAllAnswerCopiesByStudentAndSubmitted(Student student, boolean submitted);
	
	@Cacheable
	Iterable<AnswerCopy> getAllAnswerCopiesByAssignmentTrainerAndSubmitted(Trainer trainer, boolean submitted);
	
	@Cacheable
	AnswerCopy getAnswerCopyById(Integer id);
	
	@CacheEvict
	AnswerCopy updateAnswerCopy(AnswerCopy answerCopy, Integer id);
	
	@CacheEvict
	void deleteAnswerCopy(Integer id);
}
