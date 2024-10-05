package com.sampleapp.dao;

import java.util.List;

import com.sampleapp.dao.GenericDAO;
import com.sampleapp.domain.Task;





public interface TaskDAO extends GenericDAO<Task, Integer> {
  
	List<Task> findAll();
	






}


