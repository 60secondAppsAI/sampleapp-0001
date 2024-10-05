package com.sampleapp.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.sampleapp.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.sampleapp.domain.Attachment;
import com.sampleapp.dto.AttachmentDTO;
import com.sampleapp.dto.AttachmentSearchDTO;
import com.sampleapp.dto.AttachmentPageDTO;
import com.sampleapp.service.AttachmentService;
import com.sampleapp.dto.common.RequestDTO;
import com.sampleapp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/attachment")
@RestController
public class AttachmentController {

	private final static Logger logger = LoggerFactory.getLogger(AttachmentController.class);

	@Autowired
	AttachmentService attachmentService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Attachment> getAll() {

		List<Attachment> attachments = attachmentService.findAll();
		
		return attachments;	
	}

	//@ReadAccess
	@GetMapping(value = "/{attachmentId}")
	@ResponseBody
	public AttachmentDTO getAttachment(@PathVariable Integer attachmentId) {
		
		return (attachmentService.getAttachmentDTOById(attachmentId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addAttachment", method = RequestMethod.POST)
	public ResponseEntity<?> addAttachment(@RequestBody AttachmentDTO attachmentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = attachmentService.addAttachment(attachmentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/attachments")
	public ResponseEntity<AttachmentPageDTO> getAttachments(AttachmentSearchDTO attachmentSearchDTO) {
 
		return attachmentService.getAttachments(attachmentSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateAttachment", method = RequestMethod.POST)
	public ResponseEntity<?> updateAttachment(@RequestBody AttachmentDTO attachmentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = attachmentService.updateAttachment(attachmentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
