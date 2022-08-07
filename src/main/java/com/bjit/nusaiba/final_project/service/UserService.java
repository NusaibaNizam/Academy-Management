package com.bjit.nusaiba.final_project.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import com.bjit.nusaiba.final_project.entity.User;
import com.bjit.nusaiba.final_project.exception.UserNotFoundExeption;

@EnableCaching
public interface UserService {

	@CacheEvict
	void saveUser(User user) throws Exception;
	
	@Cacheable
	Iterable<User> getUsersMatchingName(String name) throws UserNotFoundExeption;
	
	@Cacheable
	User getUserByName(String name) throws UserNotFoundExeption;
	
	@Cacheable
	User getUserByEmail(String email) throws UserNotFoundExeption;
	
	@Cacheable
	Iterable<User> getAllUsers() throws UserNotFoundExeption;
	
	@Cacheable
	User getUserById(Integer id) throws UserNotFoundExeption;
	
	@CacheEvict
	User updateUser(User user, Integer id) throws UserNotFoundExeption;
	
	@CacheEvict
	void deleteUser(Integer id) throws UserNotFoundExeption;
	
	
}
