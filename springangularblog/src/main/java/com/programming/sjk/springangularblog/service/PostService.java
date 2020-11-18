package com.programming.sjk.springangularblog.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.programming.sjk.springangularblog.dto.PostDto;
import com.programming.sjk.springangularblog.model.Post;
import com.programming.sjk.springangularblog.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private AuthService authService;
	@Autowired
	private PostRepository postRepository;
	
	public void createPost(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		User username = authService.getCurrentUser().orElseThrow(() ->
				new IllegalArgumentException("No user logged in"));
		post.setUsername(username.getUsername());
		post.setCreatedOn(Instant.now());
		postRepository.save(post);
	}
}
