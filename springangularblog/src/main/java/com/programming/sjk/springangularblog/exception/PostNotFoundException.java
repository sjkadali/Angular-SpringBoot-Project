package com.programming.sjk.springangularblog.exception;

public class PostNotFoundException extends RuntimeException {
	public PostNotFoundException(String message) {
		super(message);
	}
}
