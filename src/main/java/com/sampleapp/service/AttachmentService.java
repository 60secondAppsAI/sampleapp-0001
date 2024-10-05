package com.sampleapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.sampleapp.domain.Attachment;
import com.sampleapp.dto.AttachmentDTO;
import com.sampleapp.dto.AttachmentSearchDTO;
import com.sampleapp.dto.AttachmentPageDTO;
import com.sampleapp.dto.AttachmentConvertCriteriaDTO;
import com.sampleapp.service.GenericService;
import com.sampleapp.dto.common.RequestDTO;
import com.sampleapp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AttachmentService extends GenericService<Attachment, Integer> {

	List<Attachment> findAll();

	ResultDTO addAttachment(AttachmentDTO attachmentDTO, RequestDTO requestDTO);

	ResultDTO updateAttachment(AttachmentDTO attachmentDTO, RequestDTO requestDTO);

    Page<Attachment> getAllAttachments(Pageable pageable);

    Page<Attachment> getAllAttachments(Specification<Attachment> spec, Pageable pageable);

	ResponseEntity<AttachmentPageDTO> getAttachments(AttachmentSearchDTO attachmentSearchDTO);
	
	List<AttachmentDTO> convertAttachmentsToAttachmentDTOs(List<Attachment> attachments, AttachmentConvertCriteriaDTO convertCriteria);

	AttachmentDTO getAttachmentDTOById(Integer attachmentId);







}





