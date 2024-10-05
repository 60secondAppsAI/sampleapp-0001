package com.sampleapp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class IssueDTO {

	private Integer issueId;

	private String title;

	private String description;

	private String status;

	private Date createdDate;

	private User userId;






}
