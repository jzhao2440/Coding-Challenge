package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

@RestController
public class ReportingStructureController {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private ReportingStructureService reportingStructureService;
	
	@GetMapping("/reportingstructure/{id}")
    public ResponseEntity<ReportingStructure> read(@PathVariable String id) {
        LOG.debug("Received reporting structure read request for id [{}]", id);
        ReportingStructure rs = reportingStructureService.read(id);
        if(rs.getEmployee()==null)  return new ResponseEntity<ReportingStructure>(HttpStatus.NOT_FOUND);        
        else  return new ResponseEntity<ReportingStructure>(rs, HttpStatus.FOUND);
    }
}
