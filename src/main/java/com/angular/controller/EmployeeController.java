package com.angular.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angular.entity.Employee;
import com.angular.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/a1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepository;
	

	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return empRepository.findAll();
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee){
		return empRepository.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Long id){
		Optional<Employee> employee = empRepository.findById(id);
		return ResponseEntity.ok(employee);
		
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){
		
		Employee employee = empRepository.findById(id).orElse(null);
		
		employee.setEname(employeeDetails.getEname());
		employee.setEmail(employeeDetails.getEmail());
		
		empRepository.save(employee);

		return ResponseEntity.ok(employee);
	}
 	
	
}
