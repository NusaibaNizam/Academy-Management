package com.bjit.nusaiba.final_project.entity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="uId")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
     
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@ManyToOne
    @JoinColumn(name="batchId")
    private Batch batch;
	
	private boolean accepted;
	
	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return accepted;
	}
}
