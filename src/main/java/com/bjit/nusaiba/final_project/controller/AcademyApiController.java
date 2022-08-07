package com.bjit.nusaiba.final_project.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bjit.nusaiba.final_project.config.JwtTokenUtill;
import com.bjit.nusaiba.final_project.entity.AnswerCopy;
import com.bjit.nusaiba.final_project.entity.Assignment;
import com.bjit.nusaiba.final_project.entity.Batch;
import com.bjit.nusaiba.final_project.entity.Course;
import com.bjit.nusaiba.final_project.entity.JwtRequest;
import com.bjit.nusaiba.final_project.entity.JwtResponse;
import com.bjit.nusaiba.final_project.entity.Role;
import com.bjit.nusaiba.final_project.entity.Student;
import com.bjit.nusaiba.final_project.entity.Trainer;
import com.bjit.nusaiba.final_project.entity.TrainingRecord;
import com.bjit.nusaiba.final_project.entity.TrainingSession;
import com.bjit.nusaiba.final_project.entity.User;
import com.bjit.nusaiba.final_project.exception.UserNotFoundExeption;
import com.bjit.nusaiba.final_project.service.AcademyServiceImpl;
import com.bjit.nusaiba.final_project.util.UserDetailService;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AcademyApiController {

	@Autowired
	private AcademyServiceImpl service;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtill jwtTokenUtil;

	@Autowired
	private UserDetailService userDetailsService;
	
	
/***********************************Trainees********************************************/
	
	

	@RequestMapping(method = RequestMethod.GET, value = "/api/trainees")
	public ResponseEntity<Object> getTrainees(@RequestParam("email") @Nullable String email,
			@RequestParam("id") @Nullable Integer id,
			@RequestParam("name") @Nullable String name,
			@RequestParam("accepted") @Nullable Boolean accepted
			) 
					 throws UserNotFoundExeption{
		Object body;
		if(id!=null) {
			body=service.getStudentById(id);
		}else if (email!=null) {
			body=service.getStudentByEmail(email);
		}else if (name!=null&& accepted!=null) {
			body=(List<Student>) service.getStudentMatchingName(accepted, name);
		}else if (accepted!=null) {
			body=(List<Student>) service.getStudentByAccepted(accepted);
		}else {
			body=(List<Student>) service.getAllStudents();
		}
		
		return new ResponseEntity<Object>(body,HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST , value = "/trainee_register")
	public ResponseEntity<Object> registerTrainees(@RequestBody @Valid Student student) throws Exception{
		student.setPass((new BCryptPasswordEncoder()).encode(student.getPass()));
		Set<Role> roles=new HashSet<>();
		roles.add(service.getRoleById(1));
		student.setRoles(roles);
		service.saveStudent(student);
		return new ResponseEntity<>(student,HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT , value = "/api/trainees")
	public ResponseEntity<Object> putTrainees(@RequestParam("id") Integer id, @RequestBody @Valid Student student) throws UserNotFoundExeption{
		Student studentDb=service.getStudentById(id);
		if(!studentDb.getPass().equals(student.getPass())) {
			student.setPass((new BCryptPasswordEncoder()).encode(student.getPass()));
		}
	
		service.updateStudent(student,id);
		return new ResponseEntity<>(studentDb,HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.DELETE , value = "/api/trainees")
	public ResponseEntity<Object> deleteTrainees(@RequestParam("id") Integer id) throws UserNotFoundExeption{
		service.getAllAnswerCopiesByStudent(service.getStudentById(id)).forEach(ans->{
			service.deleteAnswerCopy(ans.getAnswerCopyId());
		});
		
		service.deleteStudent(id);
		
		return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
	}
	
	
	
/***********************************Trainers********************************************/
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/trainers")
	public ResponseEntity<Object> getTrainers(@RequestParam("email") @Nullable String email,
			@RequestParam("id") @Nullable Integer id,
			@RequestParam("name") @Nullable String name,
			@RequestParam("accepted") @Nullable Boolean accepted
			)  
					 throws UserNotFoundExeption{
		Object body;
		if(id!=null) {
			body=service.getTrainerById(id);
		}else if (email!=null) {
			body=service.getTrainerByEmail(email);
		}else if (name!=null&& accepted!=null) {
			body=(List<Trainer>) service.getTrainerMatchingName(accepted, name);
		}else if (accepted!=null) {
			body=(List<Trainer>) service.getTrainerByAccepted(accepted);
		}else {
			body=(List<Trainer>) service.getAllTariner();
		}
		
		return new ResponseEntity<Object>(body,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/trainer_register")
	public ResponseEntity<Object> registerTrainers(@RequestBody @Valid Trainer trainer) throws Exception{
		trainer.setPass((new BCryptPasswordEncoder()).encode(trainer.getPass()));
		Set<Role> roles=new HashSet<>();
		roles.add(service.getRoleById(2));
		trainer.setRoles(roles);
		service.saveTrainer(trainer);
		return new ResponseEntity<>(trainer,HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT , value = "/api/trainers")
	public ResponseEntity<Object> putTrainers(@RequestParam("id") Integer id, @RequestBody @Valid Trainer trainer) throws UserNotFoundExeption{
		Trainer trainerDb=service.getTrainerById(id);
		if(!trainerDb.getPass().equals(trainer.getPass())) {
			trainer.setPass((new BCryptPasswordEncoder()).encode(trainer.getPass()));
		}
		service.updateTrainer(trainer,id);
		return new ResponseEntity<>(trainer,HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.DELETE , value = "/api/trainers")
	public ResponseEntity<Object> deleteTrainers(@RequestParam("id") Integer id) throws UserNotFoundExeption{
		
		service.deleteTrainer(id);
		
		return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
	}
	
	
	
/***********************************Users********************************************/
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/users")
	public ResponseEntity<Object> getUsers(@RequestParam("email") @Nullable String email,
			@RequestParam("id") @Nullable Integer id,
			@RequestParam("name") @Nullable String name) 
					 throws UserNotFoundExeption{
		Object body;
		if(id!=null) {
			body=service.getUserById(id);
		}else if (email!=null) {
			body=service.getUserByEmail(email);
		}else if (name!=null) {
			body=(List<User>) service.getUsersMatchingName(name);
		}else {
			body=(List<User>) service.getAllUsers();
		}
		
		return new ResponseEntity<Object>(body,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/api/users")
	public ResponseEntity<Object> postUsers(@RequestBody @Valid User user) throws Exception{
		user.setPass((new BCryptPasswordEncoder()).encode(user.getPass()));
		Set<Role> roles=new HashSet<>();
		roles.add(service.getRoleById(2));
		user.setRoles(roles);
		service.saveUser(user);
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT , value = "/api/users")
	public ResponseEntity<Object> putUsers(@RequestParam("id") Integer id, @RequestBody @Valid User user) throws UserNotFoundExeption{
		User userDb=service.getUserById(id);
		if(!userDb.getPass().equals(user.getPass())) {
			user.setPass((new BCryptPasswordEncoder()).encode(user.getPass()));
		}
		service.updateUser(user,id);
		return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.DELETE , value = "/api/users")
	public ResponseEntity<Object> deleteUsers(@RequestParam("id") Integer id) throws UserNotFoundExeption{
		service.deleteUser(id);
		
		return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
	}
	
	
	
/***********************************Course ********************************************/
	
	

	@RequestMapping(method = RequestMethod.GET, value = "/api/course")
	public ResponseEntity<Object> getCourse(
			@RequestParam("id") @Nullable Integer id,
			@RequestParam("name") @Nullable String name,
			@RequestParam("trainerId") @Nullable Integer trainerId
			) throws UserNotFoundExeption{
		Object body;
		if(id!=null) {
			body=service.getCourseById(id);
		}else if (name!=null) {
			body=(List<Course>) service.getCourseMactchingName(name);
		}else if (trainerId!=null) {
			body=(List<Course>) service.getCourseByTariner(service.getTrainerById(trainerId));
		}else {
			body=(List<Course>) service.getAllCourse();
		}
		
		return new ResponseEntity<Object>(body,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/api/course")
	public ResponseEntity<Object> postCourse(@RequestBody Course course) throws UserNotFoundExeption{
		course.setTrainer(service.getTrainerById(course.getTrainer().getUId()));
		service.saveCourse(course);
		return new ResponseEntity<>(course,HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT , value = "/api/course")
	public ResponseEntity<Object> putCourse(@RequestParam("id") Integer id, @RequestBody Course course){
		service.updateCourse(course,id);
		return new ResponseEntity<>(course,HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.DELETE , value = "/api/course")
	public ResponseEntity<Object> deleteCourse(@RequestParam("id") Integer id){
		service.getBatchByCourse(service.getCourseById(id)).forEach(batch->{
			batch.getCourses().remove(service.getCourseById(id));
			service.saveBatch(batch);
		});
		service.getTrainingSessionsByCourse(service.getCourseById(id)).forEach(session->{
			service.deleteTrainingSession(session.getSessionId());
		});
		
		
		service.deleteCourse(id);
		
		return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
	}	
	
	
	
/***********************************Batch ********************************************/
	
	

	@RequestMapping(method = RequestMethod.GET, value = "/api/batch")
	public ResponseEntity<Object> getBatch(
			@RequestParam("id") @Nullable Integer id,
			@RequestParam("name") @Nullable String name,
			@RequestParam("trainerId") @Nullable Integer trainerId) throws UserNotFoundExeption{
		Object body;
		if(id!=null) {
			body=service.getBatchById(id);
		}else if (name!=null) {
			body=(List<Batch>) service.getBatchMatchingName(name);
		}else if (trainerId!=null) {
			body=(List<Batch>) service.getBatchByCourseTraner(service.getTrainerById(trainerId));
		}else {
			body=(List<Batch>) service.getAllBatch();
		}
		
		return new ResponseEntity<Object>(body,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/api/batch")
	public ResponseEntity<Object> postBatch(@RequestBody Batch batch){
		Set<Course> courses=new HashSet<>();
		batch.getCourses().stream().forEach(course->courses.add(service.getCourseById(course.getCourseId())));
		batch.setCourses(courses);
		service.saveBatch(batch);
		return new ResponseEntity<>(batch,HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT , value = "/api/batch")
	public ResponseEntity<Object> putBatch(@RequestParam("id") Integer id, @RequestBody Batch batch){
		Set<Course> courses=new HashSet<>();
		batch.getCourses().stream().forEach(course->courses.add(service.getCourseById(course.getCourseId())));
		batch.setCourses(courses);
		service.updateBatch(batch,id);
		return new ResponseEntity<>(batch,HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.DELETE , value = "/api/batch")
	public ResponseEntity<Object> deleteBatch(@RequestParam("id") Integer id){
		
		
		service.getAssignmentsByBatch(service.getBatchById(id)).forEach(assignment->{
			service.deleteAssignment(assignment.getAssignmentId());
		});
		
		service.getTrainingRecordByBatch(service.getBatchById(id)).forEach(record->{
			service.deleteTrainingRecord(record.getRecordId());
		});
		
		try {
			service.getStudentByBatch(service.getBatchById(id)).forEach(student->{
				student.setBatch(null);
				try {
					service.saveStudent(student);
				} catch (Exception e) {
					System.out.println(e);
				}
			});
		} catch (UserNotFoundExeption e) {
			System.out.println(e);
		}
		finally {

			service.deleteBatch(id);
		}
		
		return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
	}	
	
	
	
/***********************************Assignment********************************************/
	
	

	@RequestMapping(method = RequestMethod.GET, value = "/api/assignment")
	public ResponseEntity<Object> getAssignment(
			@RequestParam("id") @Nullable Integer id,
			@RequestParam("name") @Nullable String name,
			@RequestParam("trainerId") @Nullable Integer trainerId,
			@RequestParam("traineeId") @Nullable Integer traineeId) throws UserNotFoundExeption{
		Object body;
		if(id!=null) {
			body=service.getAssignmentById(id);
		}else if (name!=null) {
			body=(List<Assignment>) service.getAssignmentsMatchingNames(name);
		}else if (trainerId!=null) {
			body=(List<Assignment>) service.getAssignmentsByTrainer(service.getTrainerById(trainerId));
		}else if (traineeId!=null) {
			body=(List<Assignment>) service.getAssignmentsByBatch(service.getStudentById(traineeId).getBatch());
		}else {
			body=(List<Assignment>) service.getAllAssignments();
		}
		
		return new ResponseEntity<Object>(body,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/api/assignment")
	public ResponseEntity<Object> postAssignment(@RequestBody Assignment assignment) throws UserNotFoundExeption{
		
		Batch batch=assignment.getBatch();
		Iterable<Student>students=service.getStudentByBatch(batch);
		service.saveAssignment(assignment);
		students.forEach(student->{
			AnswerCopy answerCopy=new AnswerCopy();
			answerCopy.setAssignment(assignment);
			answerCopy.setStudent(student);
			service.saveAnswerCopy(answerCopy);
		});
		return new ResponseEntity<>(assignment,HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT , value = "/api/assignment")
	public ResponseEntity<Object> putAssignment(@RequestParam("id") Integer id, @RequestBody Assignment assignment) throws UserNotFoundExeption{
		service.getAllAnswerCopiesByAssignment(service.getAssignmentById(id)).forEach(ansCopy->{
			if(ansCopy!=null &&!ansCopy.isSubmmited()) {
				service.deleteAnswerCopy(ansCopy.getAnswerCopyId());
			}
		});
		Batch batch=assignment.getBatch();
		service.getStudentByBatch(batch).forEach(student->{
			AnswerCopy answerCopy=new AnswerCopy();
			answerCopy.setAssignment(service.getAssignmentById(id));
			answerCopy.setStudent(student);
			service.saveAnswerCopy(answerCopy);
		});
		service.updateAssignment(assignment,id);
		return new ResponseEntity<>(assignment,HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.DELETE , value = "/api/assignment")
	public ResponseEntity<Object> deleteAssignment(@RequestParam("id") Integer id){
		List<AnswerCopy> answerCopies=(List<AnswerCopy>) service.getAllAnswerCopiesByAssignment(service.getAssignmentById(id));
		answerCopies.forEach(answerCopy->{
			service.deleteAnswerCopy(answerCopy.getAnswerCopyId());
		});
		service.deleteAssignment(id);
		
		return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
	}
	
	
	
/***********************************Role********************************************/
	
	

	@RequestMapping(method = RequestMethod.GET, value = "/api/role")
	public ResponseEntity<Object> getRole(
			@RequestParam("id") @Nullable Integer id,
			@RequestParam("name") @Nullable String name){
		Object body;
		if(id!=null) {
			body=service.getRoleById(id);
		}else if (name!=null) {
			body=service.getRoleByName(name);
		}else {
			body=(List<Role>) service.getAllRoles();
		}
		
		return new ResponseEntity<Object>(body,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/api/role")
	public ResponseEntity<Object> postRole(@RequestBody Role role){
		service.saveRole(role);
		return new ResponseEntity<>(role,HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.DELETE , value = "/api/role")
	public ResponseEntity<Object> deleteRole(@RequestParam("id") Integer id){
		service.deleteRole(id);
		
		return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
	}
	
	
	
/***********************************TrainingRecord********************************************/
	
	

	@RequestMapping(method = RequestMethod.GET, value = "/api/record")
	public ResponseEntity<Object> getTrainingRecord(
			@RequestParam("id") @Nullable Integer id,
			@RequestParam("name") @Nullable String name){
		Object body;
		if(id!=null) {
			body=service.getTrainingRecordById(id);
		}else if (name!=null) {
			body=(List<TrainingRecord>) service.getTrainingRecordMactchingName(name);
		}else {
			body=(List<TrainingRecord>) service.getAllTrainingRecords();
		}
		
		return new ResponseEntity<Object>(body,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/api/record")
	public ResponseEntity<Object> postTrainingRecord(@RequestBody TrainingRecord trainingRecord){
		service.saveTrainingRecord(trainingRecord);
		return new ResponseEntity<>(trainingRecord,HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT , value = "/api/record")
	public ResponseEntity<Object> putTrainingRecord(@RequestParam("id") Integer id, @RequestBody TrainingRecord trainingRecord){
		service.updateTrainingRecord(trainingRecord,id);
		return new ResponseEntity<>(trainingRecord,HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.DELETE , value = "/api/record")
	public ResponseEntity<Object> deleteTrainingRecord(@RequestParam("id") Integer id){
		service.deleteTrainingRecord(id);
		
		return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
	}	

	
/***********************************TrainingSession ********************************************/
	@RequestMapping(method = RequestMethod.GET, value = "/api/session")
	public ResponseEntity<Object> getTrainingSession(
			@RequestParam("id") @Nullable Integer id,
			@RequestParam("name") @Nullable String name,
			@RequestParam("trainerId") @Nullable Integer trainerId,
			@RequestParam("traineeId") @Nullable Integer traineeId,
			@RequestParam("weekday") @Nullable Integer weekday) throws UserNotFoundExeption{
		Object body;
		if(id!=null) {
			body=service.getTrainingSessionById(id);
		}else if (name!=null) {
			body=(List<TrainingSession>) service.getTrainingSessionMactchingName(name);
		}else if (trainerId!=null&&weekday!=null) {
			body=(List<TrainingSession>) service.getTrainingSessionsByTrainerAndWeekday(service.getTrainerById(trainerId), weekday);
		}else if (trainerId!=null) {
			body=(List<TrainingSession>) service.getTrainingSessionsByTrainer(service.getTrainerById(trainerId));
		}else if (traineeId!=null&&weekday!=null) {
			Set<TrainingSession> trainingSessions=new HashSet<>();
			Set<Course> courses=service.getStudentById(traineeId).getBatch().getCourses();			
			courses.forEach(course->{
				service.getTrainingSessionsByCourseAndWeekDay(course,weekday).forEach(session->{
					trainingSessions.add(session);
				});
			});
			body=trainingSessions;
		}else if (traineeId!=null) {
			Set<TrainingSession> trainingSessions=new HashSet<>();
			Set<Course> courses=service.getStudentById(traineeId).getBatch().getCourses();			
			courses.forEach(course->{
				service.getTrainingSessionsByCourse(course).forEach(session->{
					trainingSessions.add(session);
				});
			});
			body=trainingSessions;
		}else {
			body=(List<TrainingSession>) service.getAllTrainingSessions();
		}
		
		return new ResponseEntity<Object>(body,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/api/session")
	public ResponseEntity<Object> postTrainingSession(@RequestBody TrainingSession trainingSession) throws Exception{
		service.saveTrainingSession(trainingSession);
		return new ResponseEntity<>(trainingSession,HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT , value = "/api/session")
	public ResponseEntity<Object> putTrainingSession(@RequestParam("id") Integer id, @RequestBody TrainingSession trainingSession) throws Exception{
		service.updateTrainingSession(trainingSession,id);
		return new ResponseEntity<>(trainingSession,HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.DELETE , value = "/api/session")
	public ResponseEntity<Object> deleteTrainingSession(@RequestParam("id") Integer id){
		service.deleteTrainingSession(id);
		
		return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
	}	
	
	
	
/***********************************AnswerCopy ********************************************/
	
	

	@RequestMapping(method = RequestMethod.GET, value = "/api/answer_copy")
	public ResponseEntity<Object> getAnswerCopy(@RequestParam("traineeId") @Nullable Integer traineeId,
			@RequestParam("id") @Nullable Integer id,
			@RequestParam("trainerId") @Nullable Integer trainerId,
			@RequestParam("submitted") @Nullable Boolean submitted
			) throws UserNotFoundExeption {
		Object body;
		if(id!=null) {
			body=service.getAnswerCopyById(id);
		}else if (trainerId!=null&& submitted!=null) {
			body=(List<AnswerCopy>) service.getAllAnswerCopiesByAssignmentTrainerAndSubmitted(service.getTrainerById(trainerId), submitted);
		}else if (traineeId!=null&& submitted!=null) {
			body=(List<AnswerCopy>) service.getAllAnswerCopiesByStudentAndSubmitted(service.getStudentById(traineeId), submitted);
		}else if (submitted!=null) {
			body=(List<AnswerCopy>) service.getAllAnswerCopiesBySubmitted(submitted);
		}else if (traineeId!=null) {
			body=(List<AnswerCopy>) service.getAllAnswerCopiesByStudent(service.getStudentById(traineeId));
		}else {
			body=(List<AnswerCopy>) service.getAllAnswerCopies();
		}
		
		return new ResponseEntity<Object>(body,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/api/answer_copy")
	public ResponseEntity<Object> postAnswerCopy(@RequestBody AnswerCopy answerCopy){
		service.saveAnswerCopy(answerCopy);
		return new ResponseEntity<>(answerCopy,HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method = RequestMethod.PUT , value = "/api/answer_copy")
	public ResponseEntity<Object> putAnswerCopy(@RequestParam("id") Integer id, @RequestBody  AnswerCopy answerCopy){
		service.updateAnswerCopy(answerCopy, id);
		return new ResponseEntity<>(answerCopy,HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.DELETE , value = "/api/answer_copy")
	public ResponseEntity<Object> deleteAnswerCopy(@RequestParam("id") Integer id) throws UserNotFoundExeption{
		service.deleteAnswerCopy(id);
		
		return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
	}
	
	
	
	/***************************Authentication*****************************/


	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		final User userDetails = (User) userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		authenticate(userDetails, authenticationRequest.getPassword());



		final String token = jwtTokenUtil.generateToken(userDetails);

		ArrayList<String> roles = userDetails.getRoles().stream().map(r->r.getName()).collect(Collectors.toCollection(ArrayList::new));
		return ResponseEntity.ok(new JwtResponse(token,jwtTokenUtil.generateRefreshToken(userDetails),roles,userDetails.getName(),userDetails.getUId()));
	}
	
	@RequestMapping(value = "/refresh")
	public ResponseEntity<?> createAuthenticationTokenFromRefreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		String token = null;
		User userDetails = null;
		ArrayList<String> roles=new ArrayList<String>();
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}catch(Exception e) {
				System.out.println(e.getClass());
			}
		} else {
			throw new Exception("JWT Token does not begin with Bearer String"+requestTokenHeader);
		}

		if (username != null) {

			userDetails = (User) userDetailsService.loadUserByUsername(username);
			
			
			token = jwtTokenUtil.generateToken(userDetails);

			roles = userDetails.getRoles().stream().map(r->r.getName()).collect(Collectors.toCollection(ArrayList::new));
		}else {
			throw new Exception("Invalid Token");
		}

	
		return ResponseEntity.ok(new JwtResponse(token,jwtToken,roles,userDetails.getName(),userDetails.getUId()));

	}

	private void authenticate(UserDetails userDetails, String pass) throws Exception {
		try {
			UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(userDetails.getUsername(), pass ,userDetails.getAuthorities());
			authenticationManager.authenticate(token);
			
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
