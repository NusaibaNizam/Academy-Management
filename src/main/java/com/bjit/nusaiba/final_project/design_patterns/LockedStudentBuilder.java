package com.bjit.nusaiba.final_project.design_patterns;

public class LockedStudentBuilder extends StudentBuilder {

	@Override
	public void setAccepted() {
		student.setAccepted(false);
		
	}

}
