package com.sampleapp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class CommentDTO {

	private Integer commentId;

	private String content;

	private Date createdDate;

	private Issue issueId;

	private User userId;






}
