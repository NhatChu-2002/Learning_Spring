package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
		};
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
