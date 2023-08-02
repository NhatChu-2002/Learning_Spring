package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.dao.AppDAOImpl;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO)
	{
		return runner->{
//			createCourseAndReviews(appDAO);

//			retrieveCourseAndReviews(appDAO);

			deleteCourseAndReviews(appDAO);
		};

	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		int theId = 10;

		System.out.println("Delete the course id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done! ");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		// get the course and reviews
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		//print the course
		System.out.println(tempCourse);

		//print the reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("Done!");

	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("Pacman - how to score 10000 points");

		// add some review
		tempCourse.addReview(new Review("Great course! "));
		tempCourse.addReview(new Review("Good course! "));
		tempCourse.addReview(new Review("Nice course! "));
		tempCourse.addReview(new Review("Dumb course :(( "));

		// save the course
		System.out.println("Saving the course! ");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done!");



	}

	private void deleteCourse(AppDAO appDAO) {

		int theId = 10;

		System.out.println("Deleting the course id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {

		int theId = 10;

		// find the course

		System.out.println("Finding course by id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		System.out.println("Updating the course id: " +  theId);
		tempCourse.setTitle("Enjoy the Simple things");

		appDAO.update(tempCourse);

		System.out.println("Done!");

	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 1;
		// find the instructor

		System.out.println("Finding the Instructor with id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		// update
		System.out.println("Updating the Instructor with id: " + theId);
		tempInstructor.setLastName("TESTER");

		appDAO.update(tempInstructor);
		System.out.println("Done");

	}

	private void findInstructorWithCourseJoinFetch(AppDAO appDAO) {

		int theId = 1;

		System.out.println("Finding the Instructor with id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done");


	}

	private void findCoursesForInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding the Instructor with id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + tempInstructor);

		System.out.println("Finding courses for instructor id: " + theId);

		// finding courses for the instructor
		List<Course> courses = appDAO.findCourseByInstructorId(theId);

		tempInstructor.setCourses(courses);

		System.out.println("The associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");

	}

	private void findInstructorWithCourse(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding the Instructor with id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + tempInstructor);

		System.out.println("the assosiated Courses: " + tempInstructor.getCourses());
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		Instructor tempInstructor = new Instructor("Kyle","Public","kyle@luv2code.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com", "games");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		Course tempCourse1 = new Course("Piano - the ultimate guide");
		Course tempCourse2 = new Course("The baseball masterclass");

		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The course: " + tempInstructor.getCourses());

		appDAO.save(tempInstructor);





	}

	private void deleteInstructorDetail(AppDAO appDAO) {

		int theId = 3;
		System.out.println("Deleting the instructor detail: " + theId);

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Done");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		System.out.println("the assosiated instructor: +" + tempInstructorDetail.getInstructor());

		System.out.println("Done");
	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Deleting the instructor id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);

		System.out.println("the assosiated InstructorDetail only :" + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		// create the instructor

		/*Instructor tempInstructor = new Instructor("Chad","Darby","darby@luv2code.com");
		InstructorDetail tempInstructorDetail = new InstructorDetail("luv2code_youtube", "basketball");

		tempInstructor.setInstructorDetail(tempInstructorDetail);*/


		Instructor tempInstructor = new Instructor("Nathan","Bou","nathan@luv2code.com");
		InstructorDetail tempInstructorDetail = new InstructorDetail("luv2code_youtube", "movies");

		tempInstructor.setInstructorDetail(tempInstructorDetail);
		System.out.println("Saving instructor: " + tempInstructor);

		//this will save the detail because of cascadetype = all
		appDAO.save(tempInstructor);

		System.out.println("Done");


	}
}
