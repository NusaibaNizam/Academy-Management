package com.bjit.nusaiba.final_project.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import com.bjit.nusaiba.final_project.entity.Trainer;
import com.bjit.nusaiba.final_project.exception.UserNotFoundExeption;
@EnableCaching
public interface TrainerService {

	@CacheEvict
	void saveTrainer(Trainer trainer) throws Exception;
	
	@Cacheable
	Iterable<Trainer> getTrainerMatchingName(Boolean accepted, String name) throws UserNotFoundExeption;
	
	@Cacheable
	Trainer getTrainerByName(String name) throws UserNotFoundExeption;
	
	@Cacheable
	Trainer getTrainerByEmail(String email) throws UserNotFoundExeption;
	
	@Cacheable
	Iterable<Trainer> getAllTariner() throws UserNotFoundExeption;
	
	@Cacheable
	Iterable<Trainer> getTrainerByAccepted(Boolean accepted) throws UserNotFoundExeption;
	
	@Cacheable
	Trainer getTrainerById(Integer id) throws UserNotFoundExeption;
	
	@CacheEvict
	Trainer updateTrainer(Trainer trainer, Integer id) throws UserNotFoundExeption;
	
	@CacheEvict
	void deleteTrainer(Integer id) throws UserNotFoundExeption;
	
}
