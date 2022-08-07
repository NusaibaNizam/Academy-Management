package com.bjit.nusaiba.final_project.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import com.bjit.nusaiba.final_project.entity.Batch;
import com.bjit.nusaiba.final_project.entity.Student;
import com.bjit.nusaiba.final_project.exception.UserNotFoundExeption;
@EnableCaching
public interface StudentService {
	
	@CacheEvict
	void saveStudent(Student student) throws Exception;
	
	@Cacheable
	Iterable<Student> getStudentMatchingName(Boolean accepted, String name) throws UserNotFoundExeption;
	
	@Cacheable
	Student getStudentByName(String name) throws UserNotFoundExeption;
	
	@Cacheable
	Iterable<Student> getStudentByAccepted(Boolean accepted) throws UserNotFoundExeption;
	
	@Cacheable
	Iterable<Student> getStudentByBatch(Batch batch) throws UserNotFoundExeption;
	
	@Cacheable
	Student getStudentByEmail(String email) throws UserNotFoundExeption;
	
	@Cacheable
	Iterable<Student> getAllStudents() throws UserNotFoundExeption;
	
	@Cacheable
	Student getStudentById(Integer id) throws UserNotFoundExeption;
	
	@CacheEvict
	Student updateStudent(Student student, Integer id) throws UserNotFoundExeption;
	
	@CacheEvict
	void deleteStudent(Integer id) throws UserNotFoundExeption;
}
