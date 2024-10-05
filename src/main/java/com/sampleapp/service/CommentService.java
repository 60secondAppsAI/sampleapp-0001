package com.sampleapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.sampleapp.domain.Comment;
import com.sampleapp.dto.CommentDTO;
import com.sampleapp.dto.CommentSearchDTO;
import com.sampleapp.dto.CommentPageDTO;
import com.sampleapp.dto.CommentConvertCriteriaDTO;
import com.sampleapp.service.GenericService;
import com.sampleapp.dto.common.RequestDTO;
import com.sampleapp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CommentService extends GenericService<Comment, Integer> {

	List<Comment> findAll();

	ResultDTO addComment(CommentDTO commentDTO, RequestDTO requestDTO);

	ResultDTO updateComment(CommentDTO commentDTO, RequestDTO requestDTO);

    Page<Comment> getAllComments(Pageable pageable);

    Page<Comment> getAllComments(Specification<Comment> spec, Pageable pageable);

	ResponseEntity<CommentPageDTO> getComments(CommentSearchDTO commentSearchDTO);
	
	List<CommentDTO> convertCommentsToCommentDTOs(List<Comment> comments, CommentConvertCriteriaDTO convertCriteria);

	CommentDTO getCommentDTOById(Integer commentId);







}





