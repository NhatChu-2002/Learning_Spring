package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define to load students data only once!
    @PostConstruct
    public void loadData()
    {
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Poorima", "Patel"));
        theStudents.add(new Student("Mary", "Pei"));
        theStudents.add(new Student("Dante", "Lich"));
    }

    //return a list of Students
    @GetMapping("/students")
    public List<Student> getStudents()
    {
        return theStudents;
    }

    // define endpoint to return a student
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId)
    {
        //check theStudentId again the list size
        if((studentId>= theStudents.size()) || (studentId < 0))
        {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }

    //add an exception handler


}
