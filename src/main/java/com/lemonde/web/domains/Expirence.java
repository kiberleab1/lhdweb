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
public class Expirence {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;

	@NotNull(message = "Type Can Not Be Empty")
	@NotEmpty(message = "Type Can Not Be Empty")
	@Size(min = 1, message = "Type Should Be At Least One Character")
	private String type;

	@NotNull(message = "Detail Can Not Be Empty")
	@NotEmpty(message = "Detail Can Not Be Empty")
	@Size(min = 20, message = "Detail Should Be At Least Tewinty Character")
	@Column(columnDefinition = "TEXT")
	private String detailText;
	
	@NotNull(message = "Client Can Not Be Empty")
	@NotEmpty(message = "Client Can Not Be Empty")
	@Size(min = 1, message = "Client Should Be At Least One Character")
	private String client;
	
	@NotNull(message = "Firm Can Not Be Empty")
	@NotEmpty(message = "Firm Can Not Be Empty")
	@Size(min = 1, message = "Firm Should Be At Least One Character")
	private String firm;

}
