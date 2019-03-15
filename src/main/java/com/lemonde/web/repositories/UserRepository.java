package com.lemonde.web.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lemonde.web.domains.User;

public interface UserRepository extends CrudRepository<User,String>{
	//public User findByEmail(String Email);
	public User findByEmail(String Email);
	public List<User> findAllUsersByRole(String role);
}
