package com.lemonde.web.domains;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class OtherTexts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@NotNull
	private String type;
	
	@NotNull
	private String page;
	
	@Column(columnDefinition="TEXT")
	private String text;
	
	@OneToOne(mappedBy="otherTextId",cascade = CascadeType.PERSIST, 
            fetch = FetchType.LAZY)
	private ImageModel imageModel;
}
