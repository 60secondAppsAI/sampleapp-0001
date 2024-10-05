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
@Table(name="attachments")
@Getter @Setter @NoArgsConstructor
public class Attachment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="attachment_id")
	private Integer attachmentId;
    
  	@Column(name="file_name")
	private String fileName;
    
  	@Column(name="file_type")
	private String fileType;
    
  	@Column(name="file_size")
	private Integer fileSize;
    
  	@Column(name="issue_id")
	private Issue issueId;
    
	




}
