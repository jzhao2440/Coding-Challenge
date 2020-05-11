package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {
	 private String reportingStructureIdUrl;

	 @Autowired
	 private ReportingStructureService reportingStructureService;

	 @LocalServerPort
	 private int port;

	 @Autowired
	 private TestRestTemplate restTemplate;
	 
	 @Before
	 public void setup() {
	     reportingStructureIdUrl = "http://localhost:" + port + "/reportingstructure/{id}";
	 }
	 
	 @Test
	 public void testRead() {
		 ReportingStructure testReportingStructure = reportingStructureService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
	     ReportingStructure readReportingStructure = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, 
	    		                                                               testReportingStructure.getEmployee().getEmployeeId()).getBody();
	     assertReportingStructureEquivalence(testReportingStructure, readReportingStructure);
	 }
	 
	 private static void assertReportingStructureEquivalence(ReportingStructure expected, ReportingStructure actual) {
	        assertEmployeeEquivalence(expected.getEmployee(), actual.getEmployee());
	        assertEquals(expected.getNumberOfReports(), actual.getNumberOfReports());
	 }
	 
	 private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
		    assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
	        assertEquals(expected.getFirstName(), actual.getFirstName());
	        assertEquals(expected.getLastName(), actual.getLastName());
	        assertEquals(expected.getDepartment(), actual.getDepartment());
	        assertEquals(expected.getPosition(), actual.getPosition());
	 }
}
