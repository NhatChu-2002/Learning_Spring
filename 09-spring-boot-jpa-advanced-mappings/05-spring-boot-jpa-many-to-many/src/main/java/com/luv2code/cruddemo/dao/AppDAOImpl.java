package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.TypeElement;
import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
    // define a field for entity manager and use constructor injection

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager theEntityManager)
    {
        this.entityManager = theEntityManager;
    }
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {

        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        //get the course
        List<Course> courses = tempInstructor.getCourses();

        //break the association of all courses for the instructor
        for(Course tempCourse : courses)
        {
            tempCourse.setInstructor(null);
        }

        //delete the instructor
        entityManager.remove(tempInstructor);


    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {

        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the assosiated object reference
        //break the bi directional link
        //
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCourseByInstructorId(int theId) {

        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id =: data", Course.class
        );

        query.setParameter("data", theId);
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        // create query
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i "
                                                                    + "JOIN FETCH i.courses "
                                                                    + "JOIN FETCH i.instructorDetail "
                                                                    + "where i.id = :data", Instructor.class);
        query.setParameter("data", theId);

        Instructor instructor = query.getSingleResult();

        return  instructor;

    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {

        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        //retrieve the course
        Course tempCourse = entityManager.find(Course.class,theId);

        entityManager.remove(tempCourse);

    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {

        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                +"JOIN FETCH c.reviews "
                +"where c.id = :data", Course.class);
        query.setParameter("data", theId);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        +"JOIN FETCH c.students "
                        +"where c.id = :data", Course.class);
        query.setParameter("data", theId);

        // execute query
        Course course = query.getSingleResult();


        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {

        // create query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        +"JOIN FETCH s.courses "
                        +"where s.id = :data", Student.class);
        query.setParameter("data", theId);

        //excecute query
        Student student = query.getSingleResult();

        return student;

    }

    @Override
    @Transactional
    public void update(Student theStudent) {

        entityManager.merge(theStudent);

    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {

        Student tempStudent = entityManager.find(Student.class, theId);
        entityManager.remove(tempStudent);

    }


}
