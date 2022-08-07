package com.bjit.nusaiba.final_project.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjit.nusaiba.final_project.entity.AnswerCopy;
import com.bjit.nusaiba.final_project.entity.Assignment;
import com.bjit.nusaiba.final_project.entity.Batch;
import com.bjit.nusaiba.final_project.entity.Course;
import com.bjit.nusaiba.final_project.entity.Role;
import com.bjit.nusaiba.final_project.entity.Student;
import com.bjit.nusaiba.final_project.entity.Trainer;
import com.bjit.nusaiba.final_project.entity.TrainingRecord;
import com.bjit.nusaiba.final_project.entity.TrainingSession;
import com.bjit.nusaiba.final_project.entity.User;
import com.bjit.nusaiba.final_project.exception.UserNotFoundExeption;
import com.bjit.nusaiba.final_project.repository.AnswerCopyRepository;
import com.bjit.nusaiba.final_project.repository.AssignmentRepository;
import com.bjit.nusaiba.final_project.repository.BatchRepository;
import com.bjit.nusaiba.final_project.repository.CourseRepository;
import com.bjit.nusaiba.final_project.repository.RoleRepostitory;
import com.bjit.nusaiba.final_project.repository.StudentRepository;
import com.bjit.nusaiba.final_project.repository.TrainerRepository;
import com.bjit.nusaiba.final_project.repository.TrainingRecordRepository;
import com.bjit.nusaiba.final_project.repository.TrainingSessionRepository;
import com.bjit.nusaiba.final_project.repository.UserRepository;

import lombok.NonNull;

@Service
public class AcademyServiceImpl implements AssignmentService, BatchService, CourseService, RoleService, StudentService,
		TrainerService, UserService, TrainingRecordService, TrainingSessionService, AnswerCopyService {

	@Autowired
	private AssignmentRepository assignmentRepository;

	@Autowired
	private BatchRepository batchRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private RoleRepostitory roleRepostitory;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TrainerRepository trainerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TrainingRecordRepository trainingRecordRepository;

	@Autowired
	private TrainingSessionRepository trainingSessionRepository;
	

	@Autowired
	private AnswerCopyRepository answerCopyRepository;
	
	

	/******************************Assignment*************************************/

	@Override
	public void saveAssignment(Assignment assignment) {
		assignmentRepository.save(assignment);

	}

	@Override
	public Iterable<Assignment> getAssignmentsMatchingNames(String name) {
		return assignmentRepository.findByNameContainingIgnoreCase(name);
	}

	@Override
	public Iterable<Assignment> getAllAssignments() {
		return assignmentRepository.findAll();
	}

	@Override
	public void deleteAssignment(Integer id) {
		assignmentRepository.deleteById(id);

	}

	@Override
	public Assignment updateAssignment(Assignment assignment, Integer id) {
		Assignment assignmentDb = assignmentRepository.findById(id).get();
		if (assignment != null) {
			if (assignment.getName() != null) {
				assignmentDb.setName(assignment.getName());
			}
			if (assignment.getDescription() != null) {
				assignmentDb.setDescription(assignment.getDescription());
			}
			if (assignment.getDeadLine() != null) {
				assignmentDb.setDeadLine(assignment.getDeadLine());
			}
			if (assignment.getTrainer() != null) {
				assignmentDb.setTrainer(assignment.getTrainer());
			}
			if (assignment.getBatch() != null) {
				assignmentDb.setBatch(assignment.getBatch());
			}
			assignmentDb.setMark(assignment.getMark());
		}

		return assignmentRepository.save(assignmentDb);

	}

	
	@Override
	public Assignment getAssignmentById(Integer id) {
		return assignmentRepository.findById(id).get();
	}


	@Override
	public Iterable<Assignment> getAssignmentsByTrainer(Trainer trainer) {
		return assignmentRepository.findByTrainer(trainer);
	}

	
	@Override
	public Iterable<Assignment> getAssignmentsByBatch(Batch batch) {
		return assignmentRepository.findByBatch(batch);
	}

	/****************************** Batch *************************************/

	@Override
	public void saveBatch(Batch batch) {
		batchRepository.save(batch);

	}

	@Override
	public Iterable<Batch> getBatchMatchingName(String name) {
		return batchRepository.findByNameContainingIgnoreCase(name);
	}
	

	@Override
	public Iterable<Batch> getBatchByCourseTraner(Trainer trainer) {
		return batchRepository.findDistinctByCourses_Trainer(trainer);
	}
	

	@Override
	public Iterable<Batch> getBatchByCourse(Course course) {
		return batchRepository.findDistinctByCourses(course);
	}

	@Override
	public Batch getBatchByName(String name) {
		return batchRepository.findByName(name);
	}

	@Override
	public Iterable<Batch> getAllBatch() {
		return batchRepository.findAll();
	}

	@Override
	public Batch updateBatch(Batch batch, Integer id) {
		Batch batchDb = batchRepository.findById(id).get();

		if (batch != null) {
			if (batch.getName() != null) {
				batchDb.setName(batch.getName());
			}
			if (batch.getCourses() != null) {
				System.out.println(batch.getCourses());
				batchDb.setCourses(batch.getCourses());
				System.out.println(batchDb);
			}
		}

		return batchRepository.save(batchDb);
	}

	@Override
	public void deleteBatch(Integer id) {
		batchRepository.deleteById(id);

	}

	@Override
	public Batch getBatchById(Integer id) {
		return batchRepository.findById(id).get();
	}

	/****************************** Course *************************************/

	@Override
	public void saveCourse(Course course) {
		courseRepository.save(course);

	}

	@Override
	public Iterable<Course> getCourseMactchingName(String name) {
		return courseRepository.findByNameContainingIgnoreCase(name);
	}


	@Override
	public Iterable<Course> getCourseByTariner(Trainer trainer) {
		return courseRepository.findByTrainer(trainer);
	}
	
	@Override
	public Course getCourseByName(String name) {
		return courseRepository.findByName(name);
	}

	@Override
	public Iterable<Course> getAllCourse() {
		return courseRepository.findAll();
	}

	@Override
	public Course updateCourse(Course course, Integer id) {
		Course courseDb = courseRepository.findById(id).get();

		if (course != null) {
			if (course.getName() != null) {
				courseDb.setName(course.getName());
			}
			if (course.getCredit() != 0) {
				courseDb.setCredit(course.getCredit());
			}
			if (course.getTrainer() != null) {
				courseDb.setTrainer(course.getTrainer());
			}
		}

		return courseRepository.save(courseDb);
	}

	@Override
	public void deleteCourse(Integer id) {
		courseRepository.deleteById(id);

	}

	@Override
	public Course getCourseById(Integer id) {
		return courseRepository.findById(id).get();
	}

	/****************************** Role *************************************/

	@Override
	public void saveRole(Role role) {
		roleRepostitory.save(role);

	}

	@Override
	public Role getRoleByName(String name) {
		return roleRepostitory.findByName(name);
	}

	@Override
	public Iterable<Role> getAllRoles() {
		return roleRepostitory.findAll();
	}

	@Override
	public void deleteRole(Integer id) {
		roleRepostitory.deleteById(id);
	}

	@Override
	public Role getRoleById(Integer id) {
		return roleRepostitory.findById(id).get();
	}

	/****************************** Student *************************************/

	@Override
	public void saveStudent(Student student) throws Exception {

		if (!student.getRoles().stream().map(r -> (r.getName())).allMatch(role -> role.equals("student"))) {
			throw new Exception();
		}
		studentRepository.save(student);

	}

	@Override
	public Iterable<Student> getStudentByAccepted(Boolean accepted) throws UserNotFoundExeption {
		List<Student> students = (List<Student>) studentRepository.findByAccepted(accepted);
		if (students == null || students.isEmpty()) {
			throw new UserNotFoundExeption("Student not found");
		}

		return students;
	}

	@Override
	public Iterable<Student> getStudentByBatch(Batch batch) throws UserNotFoundExeption {
		List<Student> students = (List<Student>) studentRepository.findByBatch(batch);
		if (students == null || students.isEmpty()) {
			throw new UserNotFoundExeption("Student not found");
		}

		return students;
	}

	@Override
	public Iterable<Student> getStudentMatchingName(Boolean accepted, String name) throws UserNotFoundExeption {
		List<Student> students = (List<Student>) studentRepository.findByAcceptedAndNameContainingIgnoreCase(accepted,
				name);
		if (students == null || students.isEmpty()) {
			throw new UserNotFoundExeption("Student not found");
		}

		return students;
	}

	@Override
	public Student getStudentByName(String name) throws UserNotFoundExeption {
		Student student = studentRepository.findByName(name);
		if (student == null) {
			throw new UserNotFoundExeption("Student not found");
		}
		return student;
	}

	@Override
	public Student getStudentByEmail(String email) throws UserNotFoundExeption {
		Student student = studentRepository.findByEmail(email);
		if (student == null) {
			throw new UserNotFoundExeption("Student not found");
		}
		return student;
	}

	@Override
	public Iterable<Student> getAllStudents() throws UserNotFoundExeption {
		List<Student> students = (List<Student>) studentRepository.findAll();
		if (students == null || students.isEmpty()) {
			throw new UserNotFoundExeption("Student not found");
		}
		return students;
	}

	@Override
	public Student updateStudent(Student student, Integer id) throws UserNotFoundExeption {
		Student studentDb = getStudentById(id);

		if (student != null) {
			if (student.getEmail() != null) {
				studentDb.setEmail(student.getEmail());
			}
			if (student.getName() != null) {
				studentDb.setName(student.getName());
			}
			if (student.getPass() != null) {
				studentDb.setPass(student.getPass());
			}
			if (student.getRoles() != null && !student.getRoles().isEmpty()) {
				studentDb.setRoles(student.getRoles());
			}
			if (student.getBatch() != null) {
				studentDb.setBatch(student.getBatch());
			}
			if (student.isAccepted()) {
				studentDb.setAccepted(true);
			}
		}

		return studentRepository.save(studentDb);
	}

	@Override
	public void deleteStudent(Integer id) throws UserNotFoundExeption {
		Student studentDb = getStudentById(id);
		studentRepository.delete(studentDb);

	}

	@Override
	public Student getStudentById(Integer id) throws UserNotFoundExeption {
		Student student = studentRepository.findById(id).orElse(null);
		if (student == null) {
			throw new UserNotFoundExeption("Student not found");
		}
		return student;
	}

	/****************************** Trainer *************************************/

	@Override
	public void saveTrainer(Trainer trainer) throws Exception {
		if (trainer.getRoles().stream().map(r -> (r.getName())).anyMatch(role -> role.equals("student"))) {
			throw new Exception();
		}
		trainerRepository.save(trainer);

	}

	@Override
	public Iterable<Trainer> getTrainerMatchingName(Boolean accepted, String name) throws UserNotFoundExeption {
		List<Trainer> trainers = (List<Trainer>) trainerRepository.findByAcceptedAndNameContainingIgnoreCase(accepted,
				name);
		if (trainers == null || trainers.isEmpty()) {
			throw new UserNotFoundExeption("Trainer not found");
		}
		return trainers;
	}

	@Override
	public Iterable<Trainer> getTrainerByAccepted(Boolean accepted) throws UserNotFoundExeption {
		List<Trainer> trainers = trainerRepository.findByAccepted(accepted);
		if (trainers == null || trainers.isEmpty()) {
			throw new UserNotFoundExeption("Trainer not found");
		}
		return trainers;
	}

	@Override
	public Trainer getTrainerByName(String name) throws UserNotFoundExeption {
		Trainer trainer = trainerRepository.findByName(name);
		if (trainer == null) {
			throw new UserNotFoundExeption("Trainer not found");
		}
		return trainer;
	}

	@Override
	public Trainer getTrainerByEmail(String email) throws UserNotFoundExeption {
		Trainer trainer = trainerRepository.findByEmail(email);
		if (trainer == null) {
			throw new UserNotFoundExeption("Trainer not found");
		}
		return trainer;
	}

	@Override
	public Iterable<Trainer> getAllTariner() throws UserNotFoundExeption {
		List<Trainer> trainers = (List<Trainer>) trainerRepository.findAll();
		if (trainers == null || trainers.isEmpty()) {
			throw new UserNotFoundExeption("Trainer not found");
		}
		return trainers;
	}

	@Override
	public Trainer getTrainerById(Integer id) throws UserNotFoundExeption {
		Trainer trainer = trainerRepository.findById(id).orElse(null);
		if (trainer == null) {
			throw new UserNotFoundExeption("Trainer not found");
		}
		return trainer;
	}

	@Override
	public Trainer updateTrainer(Trainer trainer, Integer id) throws UserNotFoundExeption {

		Trainer trainerDb = getTrainerById(id);

		if (trainer != null) {
			if (trainer.getEmail() != null) {
				trainerDb.setEmail(trainer.getEmail());
			}
			if (trainer.getName() != null) {
				trainerDb.setName(trainer.getName());
			}
			if (trainer.getPass() != null) {
				trainerDb.setPass(trainer.getPass());
			}
			if (trainer.getRoles() != null && !trainer.getRoles().isEmpty()) {
				trainerDb.setRoles(trainer.getRoles());
			}
			if (trainer.isAccepted()) {
				trainerDb.setAccepted(true);
			}
		}

		return trainerRepository.save(trainerDb);
	}

	@Override
	public void deleteTrainer(Integer id) throws UserNotFoundExeption {
		Trainer trainerDb = getTrainerById(id);
		getAssignmentsByTrainer(getTrainerById(id)).forEach(assignment->{
			assignment.setTrainer(null);
			saveAssignment(assignment);
		});
		getCourseByTariner(getTrainerById(id)).forEach(course->{
			course.setTrainer(null);
			saveCourse(course);
		});
		trainerRepository.delete(trainerDb);

	}

	/****************************** User *************************************/

	@Override
	public void saveUser(User user) throws Exception {
		if (user.getRoles().stream().map(r -> (r.getName())).anyMatch(role -> role.equals("student"))
				&& user.getRoles().stream().map(r -> (r.getName())).anyMatch(role -> role.equals("trainer"))) {
			throw new Exception();
		}
		userRepository.save(user);

	}

	@Override
	public Iterable<User> getUsersMatchingName(String name) throws UserNotFoundExeption {
		List<User> users = (List<User>) userRepository.findByNameContainingIgnoreCase(name);
		if (users == null || users.isEmpty()) {
			throw new UserNotFoundExeption("User not found");
		}
		return users;
	}

	@Override
	public User getUserByName(String name) throws UserNotFoundExeption {
		User user = userRepository.findByName(name);
		if (user == null) {
			throw new UserNotFoundExeption("User not found");
		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) throws UserNotFoundExeption {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserNotFoundExeption("User not found");
		}
		return user;
	}

	@Override
	public Iterable<User> getAllUsers() throws UserNotFoundExeption {
		List<User> users = (List<User>) userRepository.findAll();
		if (users == null || users.isEmpty()) {
			throw new UserNotFoundExeption("User not found");
		}
		return users;
	}

	@Override
	public User getUserById(Integer id) throws UserNotFoundExeption {
		User user = userRepository.findById(id).orElse(null);
		if (user == null) {
			throw new UserNotFoundExeption("User not found");
		}
		return user;
	}

	@Override
	public User updateUser(User user, Integer id) throws UserNotFoundExeption {
		User userDb = getUserById(id);

		if (user != null) {
			if (user.getEmail() != null) {
				userDb.setEmail(user.getEmail());
			}
			if (user.getName() != null) {
				userDb.setName(user.getName());
			}
			if (user.getPass() != null) {
				userDb.setPass(user.getPass());
			}
			if (user.getRoles() != null && !user.getRoles().isEmpty()) {
				userDb.setRoles(user.getRoles());
			}

		}

		return userRepository.save(userDb);
	}

	@Override
	public void deleteUser(Integer id) throws UserNotFoundExeption {
		User userDb = getUserById(id);
		userRepository.delete(userDb);

	}

	/****************************** TrainingRecord *************************************/

	@Override
	public void saveTrainingRecord(TrainingRecord trainingRecord) {
		trainingRecordRepository.save(trainingRecord);

	}

	@Override
	public Iterable<TrainingRecord> getTrainingRecordMactchingName(String name) {
		return trainingRecordRepository.findByNameContainingIgnoreCase(name);
	}

	@Override
	public Iterable<TrainingRecord> getTrainingRecordByBatch(Batch batch) {
		return trainingRecordRepository.findByBatch(batch);
	}

	@Override
	public TrainingRecord getTrainingRecordByName(String name) {
		return trainingRecordRepository.findByName(name);
	}

	@Override
	public Iterable<TrainingRecord> getAllTrainingRecords() {
		return trainingRecordRepository.findAll();
	}

	@Override
	public TrainingRecord getTrainingRecordById(Integer id) {
		return trainingRecordRepository.findById(id).get();
	}

	@Override
	public TrainingRecord updateTrainingRecord(TrainingRecord trainingRecord, Integer id) {
		TrainingRecord trainingRecordDb = getTrainingRecordById(id);
		if (trainingRecord != null) {
			if (trainingRecord.getName() != null) {
				trainingRecordDb.setName(trainingRecord.getName());
			}
			if (trainingRecord.getStartingDate() != null) {
				trainingRecordDb.setStartingDate(trainingRecord.getStartingDate());
			}
			if (trainingRecord.getEndingDate() != null) {
				trainingRecordDb.setEndingDate(trainingRecord.getEndingDate());
			}
			if (trainingRecord.getBatch() != null) {
				trainingRecordDb.setBatch(trainingRecord.getBatch());
			}

		}
		return trainingRecordRepository.save(trainingRecordDb);
	}

	@Override
	public void deleteTrainingRecord(Integer id) {
		TrainingRecord trainingRecordDb = getTrainingRecordById(id);
		trainingRecordRepository.delete(trainingRecordDb);

	}

	/****************************** TrainingSession	 *************************************/

	@Override
	public Iterable<TrainingSession> getTrainingSessionMactchingName(String name) {
		return trainingSessionRepository.findByNameContainingIgnoreCase(name);
	}

	@Override
	public TrainingSession getTrainingSessionByName(String name) {
		return trainingSessionRepository.findByName(name);
	}

	@Override
	public Iterable<TrainingSession> getAllTrainingSessions() {
		return trainingSessionRepository.findAll();
	}

	@Override
	public TrainingSession getTrainingSessionById(Integer id) {
		return trainingSessionRepository.findById(id).get();
	}

	@Override
	public TrainingSession updateTrainingSession(TrainingSession currentSession, Integer id) throws Exception {
		checkIfSessionAvailable(currentSession);

		TrainingSession trainingSessionDb = getTrainingSessionById(id);
		if (currentSession != null) {
			if (currentSession.getName() != null) {
				trainingSessionDb.setName(currentSession.getName());
			}
			if (currentSession.getStartingTime() != null) {
				trainingSessionDb.setStartingTime(currentSession.getStartingTime());
			}
			if (currentSession.getStartingTime() != null) {
				trainingSessionDb.setEndingTime(currentSession.getEndingTime());
			}
			if (currentSession.getCourse() != null) {
				trainingSessionDb.setCourse(currentSession.getCourse());
			}
			if (currentSession.getWeekDay() != null) {
				trainingSessionDb.setWeekDay(currentSession.getWeekDay());
			}
		}
		return trainingSessionRepository.save(trainingSessionDb);
	}

	@Override
	public void deleteTrainingSession(Integer id) {
		trainingSessionRepository.deleteById(id);

	}

	@Override
	public void saveTrainingSession(TrainingSession currentSession) throws Exception {
		checkIfSessionAvailable(currentSession);

		trainingSessionRepository.save(currentSession);
	}


	@Override
	public Iterable<TrainingSession> getTrainingSessionsByTrainerAndWeekday(Trainer trainer, int weekday) {
		return trainingSessionRepository.findDistinctByCourse_TrainerAndWeekDay(trainer, weekday);
	}

	@Override
	public Iterable<TrainingSession> getTrainingSessionsByTrainer(Trainer trainer) {
		return trainingSessionRepository.findDistinctByCourse_Trainer(trainer);
	}
	
	@Override
	public Iterable<TrainingSession> getTrainingSessionsByCourse(Course course) {
		return trainingSessionRepository.findByCourse(course);
	}
	
	@Override
	public Iterable<TrainingSession> getTrainingSessionsByCourseAndWeekDay(Course course,int weekday) {
		return trainingSessionRepository.findByCourseAndWeekDay(course, weekday);
	}
	
	private void checkIfSessionAvailable(TrainingSession currentSession) throws Exception {
		System.out.println("in check");
		List<TrainingSession> existingSessions = (List<TrainingSession>) trainingSessionRepository
				.findByCourseAndWeekDay(currentSession.getCourse(), currentSession.getWeekDay());

		for (int i = 0; i < existingSessions.size(); i++) {
			@NonNull
			LocalTime currentStartingTime = currentSession.getStartingTime();
			@NonNull
			LocalTime existingStartingTime = existingSessions.get(i).getStartingTime();
			@NonNull
			LocalTime currentEndingTime = currentSession.getEndingTime();
			@NonNull
			LocalTime existingEndingTime = existingSessions.get(i).getEndingTime();
			if (currentSession.getSessionId()!=existingSessions.get(i).getSessionId()) {
				
				if (currentStartingTime.equals(existingStartingTime) || currentEndingTime.equals(existingEndingTime)
						|| (currentStartingTime.compareTo(existingEndingTime) < 0
								&& existingStartingTime.compareTo(currentEndingTime) < 0)) {
					throw new Exception("Time taken!");
				}
			}

		}
	}

	
	/****************************** AnswerCopy *************************************/	
	
	@Override
	public void saveAnswerCopy(AnswerCopy answerCopy) {
		
		answerCopyRepository.save(answerCopy);
	}

	@Override
	public Iterable<AnswerCopy> getAllAnswerCopies() {
		return answerCopyRepository.findAll();
	}


	@Override
	public Iterable<AnswerCopy> getAllAnswerCopiesByStudent(Student student) {
		return answerCopyRepository.findByStudent(student);
	}

	@Override
	public Iterable<AnswerCopy> getAllAnswerCopiesBySubmitted(boolean submitted) {
		return answerCopyRepository.findBySubmmited(submitted);
	}

	@Override
	public Iterable<AnswerCopy> getAllAnswerCopiesByStudentAndSubmitted(Student student, boolean submitted) {
		return answerCopyRepository.findByStudentAndSubmmited(student, submitted);
	}

	@Override
	public Iterable<AnswerCopy> getAllAnswerCopiesByAssignmentTrainerAndSubmitted(Trainer trainer, boolean submitted) {
		return answerCopyRepository.findByAssignment_TrainerAndSubmmited(trainer, submitted);
	}

	@Override
	public AnswerCopy getAnswerCopyById(Integer id) {
		return answerCopyRepository.findById(id).get();
	}

	@Override
	public AnswerCopy updateAnswerCopy(AnswerCopy answerCopy, Integer id) {
		AnswerCopy answerCopyDb = getAnswerCopyById(id);
		if (answerCopy != null) {
			if (answerCopy.getGrantedMark() != null) {
				answerCopyDb.setGrantedMark(answerCopy.getGrantedMark());
			}
			if (answerCopy.getAnswer() != null) {
				answerCopyDb.setAnswer(answerCopy.getAnswer());
			}
			answerCopyDb.setSubmmited(answerCopy.isSubmmited());
		}
		return answerCopyRepository.save(answerCopyDb);
	}

	@Override
	public void deleteAnswerCopy(Integer id) {
		answerCopyRepository.deleteById(id);
		
	}

	@Override
	public Iterable<AnswerCopy> getAllAnswerCopiesByAssignment(Assignment assignment) {
		return answerCopyRepository.findByAssignment(assignment);
	}




}
