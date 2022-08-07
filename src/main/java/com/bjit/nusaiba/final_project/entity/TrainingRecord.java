package com.bjit.nusaiba.final_project.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
public class TrainingRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recordId;

	@NonNull
	private String name;
	
	@NonNull
	private Date startingDate;

	@NonNull
	private Date endingDate;
	
	
	@OneToOne
    @JoinColumn(name="batchId")
    private Batch batch;
}
