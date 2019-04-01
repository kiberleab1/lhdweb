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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "type"),name="other_texts",schema = "lhd")
public class OtherTexts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@NotNull
	private String type;
	
	@NotNull
	private String page;
	
	@NotEmpty(message="About Clients Cannot be Empty")
	@Column(columnDefinition="TEXT")
	private String detailText;
	
	@OneToOne(mappedBy="otherTextId",cascade = CascadeType.PERSIST, 
            fetch = FetchType.LAZY)
	private ImageModel imageModel;
}
