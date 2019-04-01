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
	@Override
	public void deleteById(long Id) {
		// TODO Auto-generated method stub
		this.testimonialRepository.deleteById(Id);
		
	}
	@Override
	public void save(Testimonies testimonies) {
		this.testimonialRepository.save(testimonies);
		
	}
	@Override
	public Testimonies findById(long Id) {
		// TODO Auto-generated method stub
		return this.testimonialRepository.findById(Id).get();
	}

}
