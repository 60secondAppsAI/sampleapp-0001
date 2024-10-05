package com.sampleapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.sampleapp.domain.Issue;
import com.sampleapp.dto.IssueDTO;
import com.sampleapp.dto.IssueSearchDTO;
import com.sampleapp.dto.IssuePageDTO;
import com.sampleapp.dto.IssueConvertCriteriaDTO;
import com.sampleapp.service.GenericService;
import com.sampleapp.dto.common.RequestDTO;
import com.sampleapp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface IssueService extends GenericService<Issue, Integer> {

	List<Issue> findAll();

	ResultDTO addIssue(IssueDTO issueDTO, RequestDTO requestDTO);

	ResultDTO updateIssue(IssueDTO issueDTO, RequestDTO requestDTO);

    Page<Issue> getAllIssues(Pageable pageable);

    Page<Issue> getAllIssues(Specification<Issue> spec, Pageable pageable);

	ResponseEntity<IssuePageDTO> getIssues(IssueSearchDTO issueSearchDTO);
	
	List<IssueDTO> convertIssuesToIssueDTOs(List<Issue> issues, IssueConvertCriteriaDTO convertCriteria);

	IssueDTO getIssueDTOById(Integer issueId);







}





