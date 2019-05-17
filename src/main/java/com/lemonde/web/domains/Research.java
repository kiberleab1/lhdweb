package com.lemonde.web.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Research {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@NotNull(message = "Title Can Not Be Empty")
	@NotEmpty(message = "Title Can Not Be Empty")
	@Size(min = 1, message = "Title Should Be At Least One Character")
	private String title;

	@NotNull(message = "Methodology Not Be Empty")
	@NotEmpty(message = "Methodology Can Not Be Empty")
	@Size(min = 10, message = "Methodology Should Be At Least Ten Character")
	@Column(columnDefinition = "TEXT")
	private String methodlogy;
	
	@NotNull(message = "Client Can Not Be Empty")
	@NotEmpty(message = "Client Can Not Be Empty")
	@Size(min = 1, message = "Client Should Be At Least One Character")
	@Column(columnDefinition = "TEXT")
	private String client;
	
	@NotNull(message = "Objective of the study Can Not Be Empty")
	@NotEmpty(message = "Objective of the study Can Not Be Empty")
	@Column(columnDefinition = "TEXT")
	private String objective;
	
	@NotNull(message = "Type Can Not Be Empty")
	@NotEmpty(message = "Type Can Not Be Empty")
	@Size(min = 1, message = "Type Should Be At Least One Character")
	@Column(columnDefinition = "TEXT")
	private String type;

}
