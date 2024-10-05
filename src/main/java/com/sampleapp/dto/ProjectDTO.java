package com.sampleapp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ProjectDTO {

	private Integer projectId;

	private String projectName;

	private String description;

	private Date startDate;

	private Date endDate;

	private User userId;






}
