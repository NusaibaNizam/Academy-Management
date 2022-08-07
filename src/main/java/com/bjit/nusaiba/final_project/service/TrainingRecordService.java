package com.bjit.nusaiba.final_project.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import com.bjit.nusaiba.final_project.entity.Batch;
import com.bjit.nusaiba.final_project.entity.TrainingRecord;
@EnableCaching
public interface TrainingRecordService {
	
	@CacheEvict
	void saveTrainingRecord(TrainingRecord trainingRecord);
	
	@Cacheable
	Iterable<TrainingRecord> getTrainingRecordMactchingName(String name);
	
	@Cacheable
	TrainingRecord getTrainingRecordByName(String name);
	
	@Cacheable
	Iterable<TrainingRecord> getTrainingRecordByBatch(Batch batch);
	
	@Cacheable
	Iterable<TrainingRecord> getAllTrainingRecords();
	
	@Cacheable
	TrainingRecord getTrainingRecordById(Integer id);
	
	@CacheEvict
	TrainingRecord updateTrainingRecord(TrainingRecord trainingRecord, Integer id);
	
	@CacheEvict
	void deleteTrainingRecord(Integer id);
}
