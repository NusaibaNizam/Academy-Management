package com.bjit.nusaiba.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjit.nusaiba.final_project.entity.Batch;
import com.bjit.nusaiba.final_project.entity.TrainingRecord;

public interface TrainingRecordRepository extends JpaRepository<TrainingRecord, Integer> {
	Iterable<TrainingRecord> findByNameContainingIgnoreCase(String name);
	Iterable<TrainingRecord> findByBatch(Batch batch);
	TrainingRecord findByName(String name);
}
