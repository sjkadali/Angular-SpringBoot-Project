package com.programming.sjk.springangularblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programming.sjk.springangularblog.dto.LoginRequest;
import com.programming.sjk.springangularblog.dto.RegisterRequest;
import com.programming.sjk.springangularblog.exception.SpringBlogException;
import com.programming.sjk.springangularblog.service.AuthService;
import com.programming.sjk.springangularblog.service.AuthenticationResponse;

import io.jsonwebtoken.security.InvalidKeyException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);		
	} 		
	
}
