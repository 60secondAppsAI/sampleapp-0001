package com.sampleapp.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.sampleapp.dao.GenericDAO;
import com.sampleapp.service.GenericService;
import com.sampleapp.service.impl.GenericServiceImpl;
import com.sampleapp.dao.ProjectDAO;
import com.sampleapp.domain.Project;
import com.sampleapp.dto.ProjectDTO;
import com.sampleapp.dto.ProjectSearchDTO;
import com.sampleapp.dto.ProjectPageDTO;
import com.sampleapp.dto.ProjectConvertCriteriaDTO;
import com.sampleapp.dto.common.RequestDTO;
import com.sampleapp.dto.common.ResultDTO;
import com.sampleapp.service.ProjectService;
import com.sampleapp.util.ControllerUtils;





@Service
public class ProjectServiceImpl extends GenericServiceImpl<Project, Integer> implements ProjectService {

    private final static Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Autowired
	ProjectDAO projectDao;

	


	@Override
	public GenericDAO<Project, Integer> getDAO() {
		return (GenericDAO<Project, Integer>) projectDao;
	}
	
	public List<Project> findAll () {
		List<Project> projects = projectDao.findAll();
		
		return projects;	
		
	}

	public ResultDTO addProject(ProjectDTO projectDTO, RequestDTO requestDTO) {

		Project project = new Project();

		project.setProjectId(projectDTO.getProjectId());


		project.setProjectName(projectDTO.getProjectName());


		project.setDescription(projectDTO.getDescription());


		project.setStartDate(projectDTO.getStartDate());


		project.setEndDate(projectDTO.getEndDate());


		project.setUserId(projectDTO.getUserId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		project = projectDao.save(project);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Project> getAllProjects(Pageable pageable) {
		return projectDao.findAll(pageable);
	}

	public Page<Project> getAllProjects(Specification<Project> spec, Pageable pageable) {
		return projectDao.findAll(spec, pageable);
	}

	public ResponseEntity<ProjectPageDTO> getProjects(ProjectSearchDTO projectSearchDTO) {
	
			Integer projectId = projectSearchDTO.getProjectId(); 
 			String projectName = projectSearchDTO.getProjectName(); 
 			String description = projectSearchDTO.getDescription(); 
      			String sortBy = projectSearchDTO.getSortBy();
			String sortOrder = projectSearchDTO.getSortOrder();
			String searchQuery = projectSearchDTO.getSearchQuery();
			Integer page = projectSearchDTO.getPage();
			Integer size = projectSearchDTO.getSize();

	        Specification<Project> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, projectId, "projectId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, projectName, "projectName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
 			
 			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("projectName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Project> projects = this.getAllProjects(spec, pageable);
		
		//System.out.println(String.valueOf(projects.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(projects.getTotalPages()));
		
		List<Project> projectsList = projects.getContent();
		
		ProjectConvertCriteriaDTO convertCriteria = new ProjectConvertCriteriaDTO();
		List<ProjectDTO> projectDTOs = this.convertProjectsToProjectDTOs(projectsList,convertCriteria);
		
		ProjectPageDTO projectPageDTO = new ProjectPageDTO();
		projectPageDTO.setProjects(projectDTOs);
		projectPageDTO.setTotalElements(projects.getTotalElements());
		return ResponseEntity.ok(projectPageDTO);
	}

	public List<ProjectDTO> convertProjectsToProjectDTOs(List<Project> projects, ProjectConvertCriteriaDTO convertCriteria) {
		
		List<ProjectDTO> projectDTOs = new ArrayList<ProjectDTO>();
		
		for (Project project : projects) {
			projectDTOs.add(convertProjectToProjectDTO(project,convertCriteria));
		}
		
		return projectDTOs;

	}
	
	public ProjectDTO convertProjectToProjectDTO(Project project, ProjectConvertCriteriaDTO convertCriteria) {
		
		ProjectDTO projectDTO = new ProjectDTO();
		
		projectDTO.setProjectId(project.getProjectId());

	
		projectDTO.setProjectName(project.getProjectName());

	
		projectDTO.setDescription(project.getDescription());

	
		projectDTO.setStartDate(project.getStartDate());

	
		projectDTO.setEndDate(project.getEndDate());

	
		projectDTO.setUserId(project.getUserId());

	

		
		return projectDTO;
	}

	public ResultDTO updateProject(ProjectDTO projectDTO, RequestDTO requestDTO) {
		
		Project project = projectDao.getById(projectDTO.getProjectId());

		project.setProjectId(ControllerUtils.setValue(project.getProjectId(), projectDTO.getProjectId()));

		project.setProjectName(ControllerUtils.setValue(project.getProjectName(), projectDTO.getProjectName()));

		project.setDescription(ControllerUtils.setValue(project.getDescription(), projectDTO.getDescription()));

		project.setStartDate(ControllerUtils.setValue(project.getStartDate(), projectDTO.getStartDate()));

		project.setEndDate(ControllerUtils.setValue(project.getEndDate(), projectDTO.getEndDate()));

		project.setUserId(ControllerUtils.setValue(project.getUserId(), projectDTO.getUserId()));



        project = projectDao.save(project);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ProjectDTO getProjectDTOById(Integer projectId) {
	
		Project project = projectDao.getById(projectId);
			
		
		ProjectConvertCriteriaDTO convertCriteria = new ProjectConvertCriteriaDTO();
		return(this.convertProjectToProjectDTO(project,convertCriteria));
	}







}
