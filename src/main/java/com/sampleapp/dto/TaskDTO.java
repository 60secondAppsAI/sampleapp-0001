package com.sampleapp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class TaskDTO {

	private Integer taskId;

	private String taskName;

	private String description;

	private Date dueDate;

	private String status;

	private Project projectId;






}
