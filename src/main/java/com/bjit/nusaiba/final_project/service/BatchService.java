package com.bjit.nusaiba.final_project.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import com.bjit.nusaiba.final_project.entity.Batch;
import com.bjit.nusaiba.final_project.entity.Course;
import com.bjit.nusaiba.final_project.entity.Trainer;
@EnableCaching
public interface BatchService {
	
	@CacheEvict
	void saveBatch(Batch batch);
	
	@Cacheable
	Iterable<Batch> getBatchMatchingName(String name);
	
	@Cacheable
	Iterable<Batch> getBatchByCourseTraner(Trainer trainer);
	
	@Cacheable
	Iterable<Batch> getBatchByCourse(Course course);
	
	@Cacheable
	Batch getBatchByName(String name);
	
	@Cacheable
	Iterable<Batch> getAllBatch();
	
	@Cacheable
	Batch getBatchById(Integer id);
	
	@CacheEvict
	Batch updateBatch(Batch batch, Integer id);
	
	@CacheEvict
	void deleteBatch(Integer id);
	
}
