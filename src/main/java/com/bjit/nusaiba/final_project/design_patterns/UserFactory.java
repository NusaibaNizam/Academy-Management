package com.bjit.nusaiba.final_project.design_patterns;

import java.util.HashSet;
import java.util.Set;

import com.bjit.nusaiba.final_project.entity.Role;
import com.bjit.nusaiba.final_project.entity.Student;
import com.bjit.nusaiba.final_project.entity.Trainer;
import com.bjit.nusaiba.final_project.entity.User;

public class UserFactory {
	public User getUser(String role) {
		Set<Role> roles=new HashSet<>();
		if(role.equals("student")) {
			Student student= new Student();
			roles.add(new Role(1,"student"));
			student.setRoles(roles);
			return student;
		}else if(role.equals("trainer")) {
			Trainer trainer= new Trainer();
			roles.add(new Role(2,"trainer"));
			trainer.setRoles(roles);
			return trainer;
		}else {
			User user= new User();
			roles.add(new Role(3,"admin"));
			user.setRoles(roles);
			return user;
		}
	}
}
