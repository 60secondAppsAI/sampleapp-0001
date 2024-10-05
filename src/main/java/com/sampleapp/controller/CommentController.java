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

import com.sampleapp.domain.Comment;
import com.sampleapp.dto.CommentDTO;
import com.sampleapp.dto.CommentSearchDTO;
import com.sampleapp.dto.CommentPageDTO;
import com.sampleapp.service.CommentService;
import com.sampleapp.dto.common.RequestDTO;
import com.sampleapp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/comment")
@RestController
public class CommentController {

	private final static Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	CommentService commentService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Comment> getAll() {

		List<Comment> comments = commentService.findAll();
		
		return comments;	
	}

	//@ReadAccess
	@GetMapping(value = "/{commentId}")
	@ResponseBody
	public CommentDTO getComment(@PathVariable Integer commentId) {
		
		return (commentService.getCommentDTOById(commentId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = commentService.addComment(commentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/comments")
	public ResponseEntity<CommentPageDTO> getComments(CommentSearchDTO commentSearchDTO) {
 
		return commentService.getComments(commentSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateComment", method = RequestMethod.POST)
	public ResponseEntity<?> updateComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = commentService.updateComment(commentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
