package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    //return a list of Students
    @GetMapping("/students")
    public List<Student> getStudents()
    {
        List<Student> theStudents = new ArrayList<>();
        theStudents.add(new Student("Poorima", "Patel"));
        theStudents.add(new Student("Mary", "Pei"));
        theStudents.add(new Student("Dante", "Lich"));
        return theStudents;
    }

}
