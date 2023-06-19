package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO)
	{
		return runner -> {
			//createStudent(studentDAO);

			createMultipleStudents(studentDAO);

//			readStudent(studentDAO);

			//QueryForStudents(studentDAO);

			//queryForStudentsByLastName(studentDAO);

			//updateStudent(studentDAO);

			//deleteStudent(studentDAO);

//			deleteAllStudents(studentDAO);


		};
	}

	private void deleteAllStudents(StudentDAO studentDAO){
		System.out.println("Deleting all students");

		int numRowsDeleted = studentDAO.deleteAll();

		System.out.println("Deleted row counts:" + numRowsDeleted);

	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Deleting student with id:" + studentId);

		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student base on id: primary key
		int studentId = 1;

		System.out.println("Getting student with id: " + studentId);
		Student myStudent = studentDAO.findById(studentId);

		System.out.println("Updating student ...");
		myStudent.setFirstName("Scooby");

		studentDAO.update(myStudent);

		System.out.println("Updated student:" + myStudent );
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		//get a list of Students
		List<Student> theStudents = studentDAO.findByLastName("Doe");

		//Display the list
		for(Student tempStudent : theStudents)
		{
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// get a list of Students
		List<Student> theStudents = studentDAO.findAll();


		// Display list
		for(Student tempStudent : theStudents)
		{
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Creating students ...");
		Student tempStudent1 = new Student("Daffy", "Doe","Daffy@gmail");

		System.out.println("Save the student");
		studentDAO.save(tempStudent1);

		int theId = tempStudent1.getId();
		System.out.println("SaveStudent.generateId : " + theId);

		System.out.println("retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		//create student object
		System.out.println("Creating students ...");
		Student tempStudent1 = new Student("John", "Doe","john@gmail");
		Student tempStudent2 = new Student("Mary", "Apple","mary@gmail");
		Student tempStudent3 = new Student("Kanji", "Hwang","kanji@gmail");
		//save the student object
		System.out.println("Save the student");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student
		System.out.println("create new student");
		Student tempStudent = new Student("Jasper", "C","chunhat@gmail");
		//save the student object
		System.out.println("Save the student");
		studentDAO.save(tempStudent);


		//display the id of the save student
		System.out.println("Saved student Generated id:" + tempStudent.getId());
	}
}
