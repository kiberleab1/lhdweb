package com.lemonde.web.services;

import java.util.List;

import com.lemonde.web.domains.OtherTexts;

public interface OtherTextsService {
	List<OtherTexts> findByType(String type);
	List<OtherTexts> findByPage(String page);
}
