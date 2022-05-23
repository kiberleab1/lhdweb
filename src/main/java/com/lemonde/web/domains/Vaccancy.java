package com.lemonde.web.domains;
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
public class Vaccancy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@NotNull
	@NotEmpty
	@Size(min=4,message="Title Can not be Empty")
	private String title;
	
	@NotNull
	@NotEmpty
	@Size(min=4,message="Position Can not be Empty")
	private String Qualification;
	
	@NotNull
	@NotEmpty
	@Size(min=4,message="Responsiblities Can not be Empty")
	private String responsblities;
	
	@NotNull
	@NotEmpty
	@Size(min=4,message="Conditions Can not be Empty")
	private String conditions;
	
	@NotNull
	@NotEmpty
	@Size(min=4,message="Position Can not be Empty")
	private String position;
	
	@NotNull
	@NotEmpty
	@Size(min=4,message="DeadLine Can not be Empty")
	private String deadline;
	
	private int isOn;
	
}
