package com.bjit.nusaiba.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bjit.nusaiba.final_project.entity.Batch;
import com.bjit.nusaiba.final_project.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	Iterable<Student> findByAcceptedAndNameContainingIgnoreCase (Boolean accepted,String name);
	Iterable<Student> findByAccepted(Boolean accepted);
	Iterable<Student> findByBatch(Batch batch);
	Student findByName(String name);
	Student findByEmail(String email);
}
