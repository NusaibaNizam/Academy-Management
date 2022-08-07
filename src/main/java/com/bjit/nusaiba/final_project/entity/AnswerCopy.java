package com.bjit.nusaiba.final_project.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerCopy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int answerCopyId;
	
	@ManyToOne
	private Assignment assignment;
	
	@ManyToOne
	private Student student;
	
	private Double grantedMark;
	
	private boolean submmited;
	
	private String answer;
}
