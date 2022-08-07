package com.bjit.nusaiba.final_project.design_patterns;

import com.bjit.nusaiba.final_project.entity.Student;

public abstract class StudentBuilder {

	protected Student student;
	
	public void createStudent() {
		student=new Student();
	}
	public Student getStudent() {
		return student;
	}
	public abstract void setAccepted();
}
