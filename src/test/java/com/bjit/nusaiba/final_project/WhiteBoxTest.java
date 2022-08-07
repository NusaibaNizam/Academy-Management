package com.bjit.nusaiba.final_project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bjit.nusaiba.final_project.design_patterns.AcceptedStudentBuilder;
import com.bjit.nusaiba.final_project.design_patterns.LockedStudentBuilder;
import com.bjit.nusaiba.final_project.design_patterns.StudentFetcher;
import com.bjit.nusaiba.final_project.design_patterns.UserFactory;
import com.bjit.nusaiba.final_project.entity.Course;
import com.bjit.nusaiba.final_project.entity.Student;
import com.bjit.nusaiba.final_project.entity.Trainer;
import com.bjit.nusaiba.final_project.entity.User;
import com.bjit.nusaiba.final_project.repository.CourseRepository;
import com.bjit.nusaiba.final_project.repository.StudentRepository;
import com.bjit.nusaiba.final_project.repository.UserRepository;

@SpringBootTest
class WhiteBoxTest {
	
	@MockBean
	private UserRepository userRepository;
	

	@MockBean
	private CourseRepository courseRepository;
	

	@MockBean
	private StudentRepository studentRepository;
	
	
	UserFactory userFactory=new UserFactory();
	
	Trainer trainer=(Trainer) userFactory.getUser("trainer");
	
	AcceptedStudentBuilder acceptedStudentBuilder=new AcceptedStudentBuilder();
	
	LockedStudentBuilder lockedStudentBuilder= new LockedStudentBuilder();
	
	StudentFetcher studentFetcher=new StudentFetcher();
	
	Student student;

	@BeforeAll
	public static void start() {
		System.out.println("Test Started!");
	}

	
	@Test
	public void checkIfNull() {
		assertNull(trainer.getName());
	}


	@Test
	public void checkSetterGetter() {
		trainer.setName("nusaiba");
		assertEquals("nusaiba",trainer.getName());
	}
	
	
	@Test
	public void checkCourseNameWithMockMVC() {
        Optional<Course> optCourse = Optional.of(new Course("hibernate"));
        when(courseRepository.findById(1)).thenReturn(optCourse);
        assertTrue(courseRepository.findById(1).get().getName().contains("hibernate"));
	}
	
	@Test
	public void checkUserNameWithMockMVC() {
        Optional<User> optUser = Optional.of(new User( "nusaiba@gmail.com","nusaiba", "$2a$10$cpWDtMyuxAfWKg8Ql.zbXO9cJdYJaqm2e7GvUfv1Ewcz.g.IL8Ch."));
        when(userRepository.findById(1)).thenReturn(optUser);
        assertTrue(userRepository.findById(1).get().getName().contains("nusaiba"));
	}
	@Test
	public void checkUserEmailWithMockMVC() {
        Optional<User> optUser = Optional.of(new User("nusaiba@gmail.com","nusaiba", "$2a$10$cpWDtMyuxAfWKg8Ql.zbXO9cJdYJaqm2e7GvUfv1Ewcz.g.IL8Ch."));
        when(userRepository.findById(1)).thenReturn(optUser);
        assertTrue(userRepository.findById(1).get().getEmail().contains("nusaiba@gmail.com"));
	}
	
	@Test
	public void checkUserPassWithMockMVC() {
        Optional<User> optUser = Optional.of(new User( "nusaiba@gmail.com", "nusaiba", "$2a$10$cpWDtMyuxAfWKg8Ql.zbXO9cJdYJaqm2e7GvUfv1Ewcz.g.IL8Ch."));
        when(userRepository.findById(1)).thenReturn(optUser);
        assertTrue(userRepository.findById(1).get().getPass().contains("$2a$10$cpWDtMyuxAfWKg8Ql.zbXO9cJdYJaqm2e7GvUfv1Ewcz.g.IL8Ch."));
	}
	
	@Test
	public void checkStudentAcceptedWithMockMVC() {
		studentFetcher.setStudentBuilder(acceptedStudentBuilder);
		studentFetcher.constructStudent();
        Optional<Student> optStudent = Optional.of(studentFetcher.getStudent());
        when(studentRepository.findById(1)).thenReturn(optStudent);
        assertTrue(studentRepository.findById(1).get().isAccepted());
	}
	
	@Test
	public void checkStudentLockedWithMockMVC() {
		studentFetcher.setStudentBuilder(lockedStudentBuilder);
		studentFetcher.constructStudent();
        Optional<Student> optStudent = Optional.of(studentFetcher.getStudent());
        when(studentRepository.findById(1)).thenReturn(optStudent);
        assertFalse(studentRepository.findById(1).get().isAccepted());
	}
	
	
	@AfterAll
	public static void end() {
		System.out.println("Test Has Ended");
	}
}
