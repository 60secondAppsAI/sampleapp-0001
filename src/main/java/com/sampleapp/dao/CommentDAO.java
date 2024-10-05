package com.sampleapp.dao;

import java.util.List;

import com.sampleapp.dao.GenericDAO;
import com.sampleapp.domain.Comment;





public interface CommentDAO extends GenericDAO<Comment, Integer> {
  
	List<Comment> findAll();
	






}


