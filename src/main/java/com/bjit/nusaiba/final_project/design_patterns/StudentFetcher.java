package com.bjit.nusaiba.final_project.design_patterns;

import com.bjit.nusaiba.final_project.entity.Student;

public class StudentFetcher {

	private StudentBuilder studentBuilder;
	
	public void setStudentBuilder(StudentBuilder studentBuilder) {
		this.studentBuilder=studentBuilder;
	}
	
	public Student getStudent() {
		return studentBuilder.getStudent();
	}
	
	public void constructStudent() {
		studentBuilder.createStudent();
		studentBuilder.setAccepted();
	}
	
}
