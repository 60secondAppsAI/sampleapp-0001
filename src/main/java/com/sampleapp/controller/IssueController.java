package com.sampleapp.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.sampleapp.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.sampleapp.domain.Issue;
import com.sampleapp.dto.IssueDTO;
import com.sampleapp.dto.IssueSearchDTO;
import com.sampleapp.dto.IssuePageDTO;
import com.sampleapp.service.IssueService;
import com.sampleapp.dto.common.RequestDTO;
import com.sampleapp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/issue")
@RestController
public class IssueController {

	private final static Logger logger = LoggerFactory.getLogger(IssueController.class);

	@Autowired
	IssueService issueService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Issue> getAll() {

		List<Issue> issues = issueService.findAll();
		
		return issues;	
	}

	//@ReadAccess
	@GetMapping(value = "/{issueId}")
	@ResponseBody
	public IssueDTO getIssue(@PathVariable Integer issueId) {
		
		return (issueService.getIssueDTOById(issueId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addIssue", method = RequestMethod.POST)
	public ResponseEntity<?> addIssue(@RequestBody IssueDTO issueDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = issueService.addIssue(issueDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/issues")
	public ResponseEntity<IssuePageDTO> getIssues(IssueSearchDTO issueSearchDTO) {
 
		return issueService.getIssues(issueSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateIssue", method = RequestMethod.POST)
	public ResponseEntity<?> updateIssue(@RequestBody IssueDTO issueDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = issueService.updateIssue(issueDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
