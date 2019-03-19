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
public class Services {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;

	@NotNull(message = "Name Can Not Be Empty")
	@NotEmpty(message = "Name Can Not Be Empty")
	@Size(min = 1, message = "Name Should Be At Least One Character")
	private String serviceName;

	@NotNull(message = "Name Can Not Be Empty")
	@NotEmpty(message = "Name Can Not Be Empty")
	@Size(min = 20, message = "Name Should Be At Least Tewinty Character")
	@Column(columnDefinition = "TEXT")
	private String detailText;
	
	private String svgImgPath;
}