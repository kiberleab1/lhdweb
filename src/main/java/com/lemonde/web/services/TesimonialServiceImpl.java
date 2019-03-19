package com.lemonde.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemonde.web.domains.Testimonies;
import com.lemonde.web.repositories.TestimonialRepository;

@Service
public class TesimonialServiceImpl implements TestimonialService{

	@Autowired
	private TestimonialRepository testimonialRepository;
	@Override
	public Iterable<Testimonies> findAll() {
		return this.testimonialRepository.findAll();
	}

}
