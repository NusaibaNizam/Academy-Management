package com.bjit.nusaiba.final_project.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import com.bjit.nusaiba.final_project.entity.Assignment;
import com.bjit.nusaiba.final_project.entity.Batch;
import com.bjit.nusaiba.final_project.entity.Trainer;
@EnableCaching
public interface AssignmentService {
	
	@CacheEvict
	void saveAssignment(Assignment assignment);
	
	@Cacheable
	Iterable<Assignment> getAssignmentsMatchingNames(String name);
	
	@Cacheable
	Iterable<Assignment> getAllAssignments();
	
	@Cacheable
	Iterable<Assignment> getAssignmentsByTrainer(Trainer trainer);
	
	@Cacheable
	Iterable<Assignment> getAssignmentsByBatch(Batch batch);
	
	@Cacheable
	Assignment getAssignmentById(Integer id);
	
	@CacheEvict
	Assignment updateAssignment(Assignment assignment, Integer id);
	
	@CacheEvict
	void deleteAssignment(Integer id);
}
