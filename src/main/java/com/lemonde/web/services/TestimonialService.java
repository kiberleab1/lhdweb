package com.lemonde.web.services;

import com.lemonde.web.domains.Testimonies;

public interface TestimonialService {
	Iterable<Testimonies> findAll();
}
