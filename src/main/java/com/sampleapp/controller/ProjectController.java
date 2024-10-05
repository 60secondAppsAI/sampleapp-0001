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

import com.sampleapp.domain.Project;
import com.sampleapp.dto.ProjectDTO;
import com.sampleapp.dto.ProjectSearchDTO;
import com.sampleapp.dto.ProjectPageDTO;
import com.sampleapp.service.ProjectService;
import com.sampleapp.dto.common.RequestDTO;
import com.sampleapp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/project")
@RestController
public class ProjectController {

	private final static Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	ProjectService projectService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Project> getAll() {

		List<Project> projects = projectService.findAll();
		
		return projects;	
	}

	//@ReadAccess
	@GetMapping(value = "/{projectId}")
	@ResponseBody
	public ProjectDTO getProject(@PathVariable Integer projectId) {
		
		return (projectService.getProjectDTOById(projectId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addProject", method = RequestMethod.POST)
	public ResponseEntity<?> addProject(@RequestBody ProjectDTO projectDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = projectService.addProject(projectDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/projects")
	public ResponseEntity<ProjectPageDTO> getProjects(ProjectSearchDTO projectSearchDTO) {
 
		return projectService.getProjects(projectSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateProject", method = RequestMethod.POST)
	public ResponseEntity<?> updateProject(@RequestBody ProjectDTO projectDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = projectService.updateProject(projectDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
