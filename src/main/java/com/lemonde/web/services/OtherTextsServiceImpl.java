package com.lemonde.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemonde.web.domains.OtherTexts;
import com.lemonde.web.repositories.OtherTextsRepository;

@Service
public class OtherTextsServiceImpl implements OtherTextsService {

	private OtherTextsRepository otherTextsRepository;
	
	@Autowired
	public OtherTextsServiceImpl(OtherTextsRepository otherTextsRepository) {
		this.otherTextsRepository=otherTextsRepository;
	}
	@Override
	public List<OtherTexts> findByType(String type) {
		return otherTextsRepository.findByType(type);
	}
	@Override
	public List<OtherTexts> findByPage(String page) {
		return otherTextsRepository.findByPage(page);
	}

}
