package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
	private String compensationUrl;
    private String compensationIdUrl;
    
    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }
    
    @Test
    public void testCreateRead() {
    	Compensation testCompensation = new Compensation();
    	Employee testEmployee = employeeService.read("c0c2293d-16bd-4603-8e08-638a9d18b22c");
        testCompensation.setEmployee(testEmployee);
    	testCompensation.setSalary(100000);
    	testCompensation.setEffectiveDate(LocalDate.of(2020,4,13));

        // Create checks
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();
        assertCompensationEquivalence(testCompensation, createdCompensation);

        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, 
        		                                                  createdCompensation.getEmployee().getEmployeeId()).getBody();
        assertCompensationEquivalence(createdCompensation, readCompensation);
    }
    
    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
    	assertEmployeeEquivalence(expected.getEmployee(), actual.getEmployee());
    	assertEquals(expected.getSalary(), actual.getSalary(),0.00);
    	assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }
    
    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
    	assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }
}
