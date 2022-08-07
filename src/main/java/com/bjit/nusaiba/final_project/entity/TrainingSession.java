package com.bjit.nusaiba.final_project.entity;


import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class TrainingSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sessionId;

	@NonNull
	private String name;

	@ManyToOne
	private Course course;
	
	@NonNull
	private LocalTime startingTime;
	
	@NonNull
	private LocalTime endingTime;
	
	@NonNull
	private Integer weekDay;
	

}
