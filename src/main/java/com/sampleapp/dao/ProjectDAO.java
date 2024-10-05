package com.sampleapp.dao;

import java.util.List;

import com.sampleapp.dao.GenericDAO;
import com.sampleapp.domain.Project;





public interface ProjectDAO extends GenericDAO<Project, Integer> {
  
	List<Project> findAll();
	






}


