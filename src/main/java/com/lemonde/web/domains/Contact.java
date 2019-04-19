package com.lemonde.web.domains;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Contact {
	@NotNull
	@NotEmpty
	@Size(min=5,message="Name Must Be More Than 5")
	private String name;
	
	@NotNull
	@NotEmpty
	@Size(min=5,message="Name Must Be More Than 5")
	@Email(message="Not Email type")
	private String email;
	@NotNull
	@NotEmpty
	@Size(min=5,message="Message Must Be More Than 5")
	private String message;

}
