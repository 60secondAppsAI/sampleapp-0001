package com.sampleapp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProjectSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer projectId;
	
	private String projectName;
	
	private String description;
	
	private Date startDate;
	
	private Date endDate;
	
	private User userId;
	
}
