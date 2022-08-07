package com.bjit.nusaiba.final_project.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import com.bjit.nusaiba.final_project.entity.Course;
import com.bjit.nusaiba.final_project.entity.Trainer;
@EnableCaching
public interface CourseService {
	
	@CacheEvict
	void saveCourse(Course course);
	
	@Cacheable
	Iterable<Course> getCourseMactchingName(String name);
	
	@Cacheable
	Iterable<Course> getCourseByTariner(Trainer trainer);
	
	@Cacheable
	Course getCourseByName(String name);
	
	@Cacheable
	Iterable<Course> getAllCourse();
	
	@Cacheable
	Course getCourseById(Integer id);
	
	@CacheEvict
	Course updateCourse(Course course, Integer id);
	
	@CacheEvict
	void deleteCourse(Integer id);
}
