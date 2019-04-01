package com.lemonde.web.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class TeamMembers {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@NotNull
	@NotEmpty(message="Frist Name Cannot be Empty")
	@Size(min=2,message="Frist Name size too small minimum 2 allowed")
	private String fristName;
	

	@NotNull
	@NotEmpty(message="Last Name Cannot be Empty")
	@Size(min=2,message="Last Name size too small minimum 2 allowed")
	private String lastName;
	
	@NotNull
	@NotEmpty(message="Authority Cannot be Empty")
	@Size(min=2,message="Authority size too small minimum 2 allowed")
	private String authority;
	

	private String twiter;
	

	private String facebook;
	

	private String googlePlus;
	

	private String linkdon;

	private String imgPath;
	
}
