package com.sampleapp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IssueSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer issueId;
	
	private String title;
	
	private String description;
	
	private String status;
	
	private Date createdDate;
	
	private User userId;
	
}
