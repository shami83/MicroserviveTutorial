package com.example.EmployeeSearchService.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmployeeSearchService.domain.model.Employee;
import com.example.EmployeeSearchService.service.EmployeeSearchService;

@RefreshScope 
@RestController
public class EmployeeSearchController {
	
	@Autowired
	EmployeeSearchService employeeSearchService;

	@RequestMapping("/employee/find/{id}")
	public Employee findById(@PathVariable Long id){
		return employeeSearchService.findById(id);
	}
	
	@RequestMapping("/employee/findall")
	public Collection<Employee> findAll(){
		return employeeSearchService.findAll();
	}
}
