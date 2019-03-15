package com.lemonde.web.domains;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class ImageModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	
	@NotNull
	@NotEmpty
	private String path;
	
	
    @JsonIgnore
	@javax.persistence.OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="otherTextId",nullable=true)
	private OtherTexts otherTextId;
	
}
