package com.lemonde.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lemonde.web.domains.Role;
import com.lemonde.web.domains.User;
import com.lemonde.web.repositories.RoleRepository;
import com.lemonde.web.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = this.userRepository.findByEmail(username);
		if (userDetails != null) {
			return userDetails;
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);

	}

	@Override
	public void saveAdminUser(User user) {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setName("ADMIN");
		role.setUser(user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		roleRepository.save(role);

	}

	@Override
	public List<User> findAllUserByRole(String role) {
		return userRepository.findAllUsersByRole(role);
	}

}
