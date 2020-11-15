package com.programming.sjk.springangularblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.programming.sjk.springangularblog.dto.RegisterRequest;
import com.programming.sjk.springangularblog.model.User;
import com.programming.sjk.springangularblog.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setUserName(registerRequest.getUsername());
		user.setPassword(encodePassword(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		userRepository.save(user);
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

}
