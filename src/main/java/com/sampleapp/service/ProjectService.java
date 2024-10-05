package com.sampleapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.sampleapp.domain.Project;
import com.sampleapp.dto.ProjectDTO;
import com.sampleapp.dto.ProjectSearchDTO;
import com.sampleapp.dto.ProjectPageDTO;
import com.sampleapp.dto.ProjectConvertCriteriaDTO;
import com.sampleapp.service.GenericService;
import com.sampleapp.dto.common.RequestDTO;
import com.sampleapp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ProjectService extends GenericService<Project, Integer> {

	List<Project> findAll();

	ResultDTO addProject(ProjectDTO projectDTO, RequestDTO requestDTO);

	ResultDTO updateProject(ProjectDTO projectDTO, RequestDTO requestDTO);

    Page<Project> getAllProjects(Pageable pageable);

    Page<Project> getAllProjects(Specification<Project> spec, Pageable pageable);

	ResponseEntity<ProjectPageDTO> getProjects(ProjectSearchDTO projectSearchDTO);
	
	List<ProjectDTO> convertProjectsToProjectDTOs(List<Project> projects, ProjectConvertCriteriaDTO convertCriteria);

	ProjectDTO getProjectDTOById(Integer projectId);







}





