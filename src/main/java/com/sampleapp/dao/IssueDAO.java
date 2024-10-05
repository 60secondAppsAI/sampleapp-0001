package com.sampleapp.dao;

import java.util.List;

import com.sampleapp.dao.GenericDAO;
import com.sampleapp.domain.Issue;





public interface IssueDAO extends GenericDAO<Issue, Integer> {
  
	List<Issue> findAll();
	






}


