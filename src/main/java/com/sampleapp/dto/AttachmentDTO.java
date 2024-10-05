package com.sampleapp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class AttachmentDTO {

	private Integer attachmentId;

	private String fileName;

	private String fileType;

	private Integer fileSize;

	private Issue issueId;






}
