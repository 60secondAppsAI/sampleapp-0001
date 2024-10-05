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
import com.sampleapp.dao.AttachmentDAO;
import com.sampleapp.domain.Attachment;
import com.sampleapp.dto.AttachmentDTO;
import com.sampleapp.dto.AttachmentSearchDTO;
import com.sampleapp.dto.AttachmentPageDTO;
import com.sampleapp.dto.AttachmentConvertCriteriaDTO;
import com.sampleapp.dto.common.RequestDTO;
import com.sampleapp.dto.common.ResultDTO;
import com.sampleapp.service.AttachmentService;
import com.sampleapp.util.ControllerUtils;





@Service
public class AttachmentServiceImpl extends GenericServiceImpl<Attachment, Integer> implements AttachmentService {

    private final static Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);

	@Autowired
	AttachmentDAO attachmentDao;

	


	@Override
	public GenericDAO<Attachment, Integer> getDAO() {
		return (GenericDAO<Attachment, Integer>) attachmentDao;
	}
	
	public List<Attachment> findAll () {
		List<Attachment> attachments = attachmentDao.findAll();
		
		return attachments;	
		
	}

	public ResultDTO addAttachment(AttachmentDTO attachmentDTO, RequestDTO requestDTO) {

		Attachment attachment = new Attachment();

		attachment.setAttachmentId(attachmentDTO.getAttachmentId());


		attachment.setFileName(attachmentDTO.getFileName());


		attachment.setFileType(attachmentDTO.getFileType());


		attachment.setFileSize(attachmentDTO.getFileSize());


		attachment.setIssueId(attachmentDTO.getIssueId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		attachment = attachmentDao.save(attachment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Attachment> getAllAttachments(Pageable pageable) {
		return attachmentDao.findAll(pageable);
	}

	public Page<Attachment> getAllAttachments(Specification<Attachment> spec, Pageable pageable) {
		return attachmentDao.findAll(spec, pageable);
	}

	public ResponseEntity<AttachmentPageDTO> getAttachments(AttachmentSearchDTO attachmentSearchDTO) {
	
			Integer attachmentId = attachmentSearchDTO.getAttachmentId(); 
 			String fileName = attachmentSearchDTO.getFileName(); 
 			String fileType = attachmentSearchDTO.getFileType(); 
 			Integer fileSize = attachmentSearchDTO.getFileSize(); 
  			String sortBy = attachmentSearchDTO.getSortBy();
			String sortOrder = attachmentSearchDTO.getSortOrder();
			String searchQuery = attachmentSearchDTO.getSearchQuery();
			Integer page = attachmentSearchDTO.getPage();
			Integer size = attachmentSearchDTO.getSize();

	        Specification<Attachment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, attachmentId, "attachmentId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, fileName, "fileName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, fileType, "fileType"); 
			
			spec = ControllerUtils.andIfNecessary(spec, fileSize, "fileSize"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("fileName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("fileType")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Attachment> attachments = this.getAllAttachments(spec, pageable);
		
		//System.out.println(String.valueOf(attachments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(attachments.getTotalPages()));
		
		List<Attachment> attachmentsList = attachments.getContent();
		
		AttachmentConvertCriteriaDTO convertCriteria = new AttachmentConvertCriteriaDTO();
		List<AttachmentDTO> attachmentDTOs = this.convertAttachmentsToAttachmentDTOs(attachmentsList,convertCriteria);
		
		AttachmentPageDTO attachmentPageDTO = new AttachmentPageDTO();
		attachmentPageDTO.setAttachments(attachmentDTOs);
		attachmentPageDTO.setTotalElements(attachments.getTotalElements());
		return ResponseEntity.ok(attachmentPageDTO);
	}

	public List<AttachmentDTO> convertAttachmentsToAttachmentDTOs(List<Attachment> attachments, AttachmentConvertCriteriaDTO convertCriteria) {
		
		List<AttachmentDTO> attachmentDTOs = new ArrayList<AttachmentDTO>();
		
		for (Attachment attachment : attachments) {
			attachmentDTOs.add(convertAttachmentToAttachmentDTO(attachment,convertCriteria));
		}
		
		return attachmentDTOs;

	}
	
	public AttachmentDTO convertAttachmentToAttachmentDTO(Attachment attachment, AttachmentConvertCriteriaDTO convertCriteria) {
		
		AttachmentDTO attachmentDTO = new AttachmentDTO();
		
		attachmentDTO.setAttachmentId(attachment.getAttachmentId());

	
		attachmentDTO.setFileName(attachment.getFileName());

	
		attachmentDTO.setFileType(attachment.getFileType());

	
		attachmentDTO.setFileSize(attachment.getFileSize());

	
		attachmentDTO.setIssueId(attachment.getIssueId());

	

		
		return attachmentDTO;
	}

	public ResultDTO updateAttachment(AttachmentDTO attachmentDTO, RequestDTO requestDTO) {
		
		Attachment attachment = attachmentDao.getById(attachmentDTO.getAttachmentId());

		attachment.setAttachmentId(ControllerUtils.setValue(attachment.getAttachmentId(), attachmentDTO.getAttachmentId()));

		attachment.setFileName(ControllerUtils.setValue(attachment.getFileName(), attachmentDTO.getFileName()));

		attachment.setFileType(ControllerUtils.setValue(attachment.getFileType(), attachmentDTO.getFileType()));

		attachment.setFileSize(ControllerUtils.setValue(attachment.getFileSize(), attachmentDTO.getFileSize()));

		attachment.setIssueId(ControllerUtils.setValue(attachment.getIssueId(), attachmentDTO.getIssueId()));



        attachment = attachmentDao.save(attachment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AttachmentDTO getAttachmentDTOById(Integer attachmentId) {
	
		Attachment attachment = attachmentDao.getById(attachmentId);
			
		
		AttachmentConvertCriteriaDTO convertCriteria = new AttachmentConvertCriteriaDTO();
		return(this.convertAttachmentToAttachmentDTO(attachment,convertCriteria));
	}







}
