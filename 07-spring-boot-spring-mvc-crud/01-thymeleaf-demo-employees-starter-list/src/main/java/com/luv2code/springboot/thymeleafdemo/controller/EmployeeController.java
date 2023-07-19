package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService)
	{
		employeeService = theEmployeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		//get the employees from the database
		List<Employee> theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		Employee theEmployee = new Employee();

		theModel.addAttribute("employee", theEmployee);

		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel)
	{
		//get employee from database
		Employee theEmployee = employeeService.findById(theId);

		//set the employee as the model attribute to prepopulate the form
		theModel.addAttribute("employee", theEmployee);

		return "/employees/employee-form";

	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId)	{
		employeeService.deleteById(theId);
		return "redirect:/employees/list";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee")Employee theEmployee)
	{
		//save the employee
		employeeService.save(theEmployee);

		return "redirect:/employees/list";
	}



}









