package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.ReportingStructureRepository;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

	private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);
	
	@Autowired
	private ReportingStructureRepository reportingStructureRepository;
	
	@Override
	public ReportingStructure read(String id) {
		LOG.debug("Read reporting structure from employee with id [{}]", id);
		return reportingStructureRepository.findByEmployeeId(id);
	}
}
