package com.mindex.challenge.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

@Repository
public class ReportingStructureRepository {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	private int numberOfReports;
	
	public ReportingStructure findByEmployeeId(String id) {
		ReportingStructure rs = new ReportingStructure();
		numberOfReports = 0;
		rs.setEmployee(fullFill(id));
		rs.setNumberOfReports(numberOfReports);
		return rs;
	}
	
	public Employee fullFill(String employeeId) {
		try {
		Employee employee = employeeRepository.findByEmployeeId(employeeId);
		if(employee.getDirectReports()==null)  return employee;
	    List<Employee> directReports = new ArrayList<>();
	    for(Employee e: employee.getDirectReports()) 
	    	directReports.add(fullFill(e.getEmployeeId()));
	    numberOfReports += directReports.size();
	    employee.setDirectReports(directReports);
		return employee;
		}
		catch(Exception ex) {
			return null;
		}
	}
}
