package com.sampleapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="projects")
@Getter @Setter @NoArgsConstructor
public class Project {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="project_id")
	private Integer projectId;
    
  	@Column(name="project_name")
	private String projectName;
    
  	@Column(name="description")
	private String description;
    
  	@Column(name="start_date")
	private Date startDate;
    
  	@Column(name="end_date")
	private Date endDate;
    
  	@Column(name="user_id")
	private User userId;
    
	




}
