package com.sampleapp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AttachmentSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer attachmentId;
	
	private String fileName;
	
	private String fileType;
	
	private Integer fileSize;
	
	private Issue issueId;
	
}
