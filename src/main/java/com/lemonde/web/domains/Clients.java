package com.lemonde.web.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
@Entity
public class Clients {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	private String link;
	
	@NotNull
	@NotEmpty(message="Organization Name Cannot be Empty")
	private String name;
	
	private String imgPath;
	
	private String type;
	
	private String country;
}