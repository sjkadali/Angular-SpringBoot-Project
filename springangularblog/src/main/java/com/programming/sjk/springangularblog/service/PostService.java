package com.programming.sjk.springangularblog.service;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programming.sjk.springangularblog.dto.PostDto;
import com.programming.sjk.springangularblog.exception.PostNotFoundException;
import com.programming.sjk.springangularblog.model.Post;
import com.programming.sjk.springangularblog.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private AuthService authService;
	@Autowired
	private PostRepository postRepository;
	
	@Transactional
	public void createPost(PostDto postDto) {
		Post post = mapFromDtoToPost(postDto);
		postRepository.save(post);
	}
	
	public PostDto readSinglePost(long id) {
		Post post = postRepository.findById(id).orElseThrow(()  -> new PostNotFoundException("For id "+id));
		return mapFromPostToDto(post);
	}
	
	private PostDto mapFromPostToDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		postDto.setUsername(post.getUsername());
		System.out.println("postDto: "+ postDto);
		return postDto;
	}

	private Post mapFromDtoToPost(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		User loggedInUser = authService.getCurrentUser().orElseThrow(() ->
				new IllegalArgumentException("User Not Found"));
		post.setUsername(loggedInUser.getUsername());
		post.setCreatedOn(Instant.now());
		post.setUpdatedOn(Instant.now());
		return post;
	}
	
	@Transactional
	public List<PostDto> showAllPosts() {
		List<Post> posts = postRepository.findAll();
		System.out.println("Posts: "+  posts);
		return posts.stream().map(this::mapFromPostToDto).collect(toList());
	}
}
