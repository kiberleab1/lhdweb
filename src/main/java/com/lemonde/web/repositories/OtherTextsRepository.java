package com.lemonde.web.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lemonde.web.domains.OtherTexts;

public interface OtherTextsRepository extends CrudRepository<OtherTexts,Long>{
	
	List<OtherTexts> findByType(String type);

	List<OtherTexts> findByPage(String page);

}
