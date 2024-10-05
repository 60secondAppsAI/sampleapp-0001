package com.sampleapp.dao;

import java.util.List;

import com.sampleapp.dao.GenericDAO;
import com.sampleapp.domain.Attachment;





public interface AttachmentDAO extends GenericDAO<Attachment, Integer> {
  
	List<Attachment> findAll();
	






}


