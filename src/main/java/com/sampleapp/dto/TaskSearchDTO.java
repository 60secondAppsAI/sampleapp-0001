package com.sampleapp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TaskSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer taskId;
	
	private String taskName;
	
	private String description;
	
	private Date dueDate;
	
	private String status;
	
	private Project projectId;
	
}
