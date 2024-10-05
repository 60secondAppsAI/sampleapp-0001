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
import com.sampleapp.dao.IssueDAO;
import com.sampleapp.domain.Issue;
import com.sampleapp.dto.IssueDTO;
import com.sampleapp.dto.IssueSearchDTO;
import com.sampleapp.dto.IssuePageDTO;
import com.sampleapp.dto.IssueConvertCriteriaDTO;
import com.sampleapp.dto.common.RequestDTO;
import com.sampleapp.dto.common.ResultDTO;
import com.sampleapp.service.IssueService;
import com.sampleapp.util.ControllerUtils;





@Service
public class IssueServiceImpl extends GenericServiceImpl<Issue, Integer> implements IssueService {

    private final static Logger logger = LoggerFactory.getLogger(IssueServiceImpl.class);

	@Autowired
	IssueDAO issueDao;

	


	@Override
	public GenericDAO<Issue, Integer> getDAO() {
		return (GenericDAO<Issue, Integer>) issueDao;
	}
	
	public List<Issue> findAll () {
		List<Issue> issues = issueDao.findAll();
		
		return issues;	
		
	}

	public ResultDTO addIssue(IssueDTO issueDTO, RequestDTO requestDTO) {

		Issue issue = new Issue();

		issue.setIssueId(issueDTO.getIssueId());


		issue.setTitle(issueDTO.getTitle());


		issue.setDescription(issueDTO.getDescription());


		issue.setStatus(issueDTO.getStatus());


		issue.setCreatedDate(issueDTO.getCreatedDate());


		issue.setUserId(issueDTO.getUserId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		issue = issueDao.save(issue);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Issue> getAllIssues(Pageable pageable) {
		return issueDao.findAll(pageable);
	}

	public Page<Issue> getAllIssues(Specification<Issue> spec, Pageable pageable) {
		return issueDao.findAll(spec, pageable);
	}

	public ResponseEntity<IssuePageDTO> getIssues(IssueSearchDTO issueSearchDTO) {
	
			Integer issueId = issueSearchDTO.getIssueId(); 
 			String title = issueSearchDTO.getTitle(); 
 			String description = issueSearchDTO.getDescription(); 
 			String status = issueSearchDTO.getStatus(); 
    			String sortBy = issueSearchDTO.getSortBy();
			String sortOrder = issueSearchDTO.getSortOrder();
			String searchQuery = issueSearchDTO.getSearchQuery();
			Integer page = issueSearchDTO.getPage();
			Integer size = issueSearchDTO.getSize();

	        Specification<Issue> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, issueId, "issueId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, title, "title"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
			spec = ControllerUtils.andIfNecessary(spec, status, "status"); 
			
 			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("title")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("status")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Issue> issues = this.getAllIssues(spec, pageable);
		
		//System.out.println(String.valueOf(issues.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(issues.getTotalPages()));
		
		List<Issue> issuesList = issues.getContent();
		
		IssueConvertCriteriaDTO convertCriteria = new IssueConvertCriteriaDTO();
		List<IssueDTO> issueDTOs = this.convertIssuesToIssueDTOs(issuesList,convertCriteria);
		
		IssuePageDTO issuePageDTO = new IssuePageDTO();
		issuePageDTO.setIssues(issueDTOs);
		issuePageDTO.setTotalElements(issues.getTotalElements());
		return ResponseEntity.ok(issuePageDTO);
	}

	public List<IssueDTO> convertIssuesToIssueDTOs(List<Issue> issues, IssueConvertCriteriaDTO convertCriteria) {
		
		List<IssueDTO> issueDTOs = new ArrayList<IssueDTO>();
		
		for (Issue issue : issues) {
			issueDTOs.add(convertIssueToIssueDTO(issue,convertCriteria));
		}
		
		return issueDTOs;

	}
	
	public IssueDTO convertIssueToIssueDTO(Issue issue, IssueConvertCriteriaDTO convertCriteria) {
		
		IssueDTO issueDTO = new IssueDTO();
		
		issueDTO.setIssueId(issue.getIssueId());

	
		issueDTO.setTitle(issue.getTitle());

	
		issueDTO.setDescription(issue.getDescription());

	
		issueDTO.setStatus(issue.getStatus());

	
		issueDTO.setCreatedDate(issue.getCreatedDate());

	
		issueDTO.setUserId(issue.getUserId());

	

		
		return issueDTO;
	}

	public ResultDTO updateIssue(IssueDTO issueDTO, RequestDTO requestDTO) {
		
		Issue issue = issueDao.getById(issueDTO.getIssueId());

		issue.setIssueId(ControllerUtils.setValue(issue.getIssueId(), issueDTO.getIssueId()));

		issue.setTitle(ControllerUtils.setValue(issue.getTitle(), issueDTO.getTitle()));

		issue.setDescription(ControllerUtils.setValue(issue.getDescription(), issueDTO.getDescription()));

		issue.setStatus(ControllerUtils.setValue(issue.getStatus(), issueDTO.getStatus()));

		issue.setCreatedDate(ControllerUtils.setValue(issue.getCreatedDate(), issueDTO.getCreatedDate()));

		issue.setUserId(ControllerUtils.setValue(issue.getUserId(), issueDTO.getUserId()));



        issue = issueDao.save(issue);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public IssueDTO getIssueDTOById(Integer issueId) {
	
		Issue issue = issueDao.getById(issueId);
			
		
		IssueConvertCriteriaDTO convertCriteria = new IssueConvertCriteriaDTO();
		return(this.convertIssueToIssueDTO(issue,convertCriteria));
	}







}
