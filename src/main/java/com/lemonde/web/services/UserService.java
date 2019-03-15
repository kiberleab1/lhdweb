package com.lemonde.web.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lemonde.web.domains.User;

public interface UserService extends UserDetailsService {
	User findByEmail(String email);
	User findUserByEmail(String email);
	void saveAdminUser(User user);
	List<User> findAllUserByRole(String role);
}
