package com.spring.tutorial.controller;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.tutorial.dto.EmployeeDTO;
import com.spring.tutorial.model.Address;
import com.spring.tutorial.model.Employee;
import com.spring.tutorial.model.Student;
import com.spring.tutorial.service.EmployeeService;
import com.spring.tutorial.service.StudentService;

@Controller
@RequestMapping("/employee")
public class HomeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
	public ModelAndView getEmployee(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("home"); // name of jsp page to be
														// displayed
		Optional<Employee> emp = employeeService.findById(id);
		mav.addObject("emp", emp);
		return mav;
	}

	@RequestMapping(value = "/get/exception", method = RequestMethod.GET, produces = "application/json")
	public String getEmployeeException(@PathVariable Integer id) throws Exception {
		throw new Exception("Testing Exception Message");
		// return "";
	}

	/*
	 * @RequestMapping(value = "/create/{fName}", method = RequestMethod.GET)
	 * public ModelAndView createEmployee(@PathVariable String fName){
	 * ModelAndView mav = new ModelAndView("home"); //name of jsp page to be
	 * displayed Employee emp = employeeService.create(fName);
	 * mav.addObject("emp",emp); return mav; }
	 */

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity createEmployee(@RequestBody EmployeeDTO emp) {
		employeeService.create(emp);
		String empAdded = "User-> {" + emp.toString() + "} is added";
		System.out.println(empAdded);

		Address address = new Address("Ring Road1", "Banglore", "Karnataka", "560000");
		Student student = new Student("Joe1", address);
		studentService.create(student);

		/*
		 * Look at how we have set address property of student and student
		 * property of address.Then we have called save only on student, thanks
		 * to Cascade attribute on address property of Student class, address
		 * will be saved automatically.
		 */

		return new ResponseEntity<>(HttpStatus.CREATED);
		// return "{\"Message\":\"Successfully Added\"}";
	}
}
