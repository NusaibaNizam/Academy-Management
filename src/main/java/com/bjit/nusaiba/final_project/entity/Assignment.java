package com.bjit.nusaiba.final_project.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

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
public class Assignment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int assignmentId;

	@NonNull
	private String name;
	
	@NonNull
	private String description;
	
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NonNull
	private LocalDate deadLine;
    
    @NonNull
    private Double mark;
    

    @ManyToOne
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name="batch_id")
    private Batch batch;
    

}
