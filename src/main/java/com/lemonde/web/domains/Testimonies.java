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
public class Testimonies {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long Id;
		
		@NotNull
		@NotEmpty(message="Name Can Not Be Empty")
		@Size(min=1,message="Name Should Be At Least One Character")
		private String FirstName;
		
		@NotNull
		@NotEmpty(message="Name Can Not Be Empty")
		@Size(min=1,message="Name Should Be At Least One Character")
		private String LastName;
		
		
		@NotNull
		@NotEmpty(message="Organazation Can Not Be Empty")
		@Size(min=1,message="Organazation Should Be At Least One Character")
		private String Organazation;
		
		@NotNull
		@NotEmpty(message="Authority Can Not Be Empty")
		@Size(min=1,message="Authority Should Be At Least One Character")
		private String Authority;
		
		@NotNull
		@NotEmpty(message="Testimony Can Not Be Empty")
		@Size(min=10,message="Testimony Should Be At Least Ten Character")
		@Column(columnDefinition="TEXT")
		private String detailText;
	}
