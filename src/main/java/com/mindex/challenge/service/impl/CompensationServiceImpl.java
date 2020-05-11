package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;

@Service
public class CompensationServiceImpl implements CompensationService{

	private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);
	
	@Autowired
	CompensationRepository compensationRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Compensation create(Compensation compensation) {
		LOG.debug("Creating compensation [{}]", compensation);
		String id = compensation.getEmployee().getEmployeeId();
		if(id==null || employeeRepository.findByEmployeeId(id)==null)  return null;
		return compensationRepository.insert(compensation);
	}

	@Override
	public Compensation read(String id) {
		LOG.debug("Reading compensation [{}]", id);
		return compensationRepository.findByEmployeeEmployeeId(id);
	}
}
