package com.bjit.nusaiba.final_project.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import com.bjit.nusaiba.final_project.entity.Course;
import com.bjit.nusaiba.final_project.entity.Trainer;
import com.bjit.nusaiba.final_project.entity.TrainingSession;

@EnableCaching
public interface TrainingSessionService {
	
	@CacheEvict
	void saveTrainingSession(TrainingSession trainingSession) throws Exception;
	
	@Cacheable
	Iterable<TrainingSession> getTrainingSessionMactchingName(String name);
	
	@Cacheable
	TrainingSession getTrainingSessionByName(String name);
	
	@Cacheable
	Iterable<TrainingSession> getAllTrainingSessions();
	
	@Cacheable
	Iterable<TrainingSession> getTrainingSessionsByTrainerAndWeekday(Trainer trainer, int weekday);
	
	@Cacheable
	Iterable<TrainingSession> getTrainingSessionsByTrainer(Trainer trainer);
	
	@Cacheable
	Iterable<TrainingSession> getTrainingSessionsByCourse(Course course);

	@Cacheable
	Iterable<TrainingSession> getTrainingSessionsByCourseAndWeekDay(Course course,int weekday);
	
	@CacheEvict
	TrainingSession getTrainingSessionById(Integer id);
	
	@CacheEvict
	TrainingSession updateTrainingSession(TrainingSession trainingSession, Integer id) throws Exception;
	
	void deleteTrainingSession(Integer id);
}
