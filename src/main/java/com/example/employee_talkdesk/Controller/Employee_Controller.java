package com.example.employee_talkdesk.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_talkdesk.Service.Employee_Service;
import com.example.employee_talkdesk.entity.Employee;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employee")
public class Employee_Controller {
	
	 private final Employee_Service service;

	    

	    public Employee_Controller(Employee_Service service) {
		super();
		this.service = service;
	}

		@PostMapping("/create")
	    public ResponseEntity<Map<String, Object>> createEmployee(@RequestBody Employee employee) {
	        try {
	            Employee savedEmployee = service.saveEmployee(employee);
	            Map<String, Object> response = new HashMap<>();
	            response.put("status", "success");
	            response.put("message", "Employee created successfully");
	            response.put("data", savedEmployee);
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            Map<String, Object> errorResponse = new HashMap<>();
	            errorResponse.put("status", "error");
	            errorResponse.put("message", "Failed to create employee");
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	        }
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable Long id) {
	        Optional<Employee> employee = service.getEmployeeById(id);
	        if (employee.isPresent()) {
	            Map<String, Object> response = new HashMap<>();
	            response.put("status", "success");
	            response.put("data", employee.get());
	            return ResponseEntity.ok(response);
	        } else {
	            Map<String, Object> errorResponse = new HashMap<>();
	            errorResponse.put("status", "error");
	            errorResponse.put("message", "Employee not found");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	        }
	    }

	    @GetMapping
	    public ResponseEntity<Map<String, Object>> getAllEmployees() {
	        List<Employee> employees = service.getAllEmployees();
	        Map<String, Object> response = new HashMap<>();
	        response.put("status", "success");
	        response.put("data", employees);
	        return ResponseEntity.ok(response);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable Long id) {
	        try {
	            service.deleteEmployee(id);
	            Map<String, Object> response = new HashMap<>();
	            response.put("status", "success");
	            response.put("message", "Employee deleted successfully");
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            Map<String, Object> errorResponse = new HashMap<>();
	            errorResponse.put("status", "error");
	            errorResponse.put("message", "Failed to delete employee");
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	        }
	
	

	    }}
