package com.programming.sjk.springangularblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programming.sjk.springangularblog.dto.PostDto;
import com.programming.sjk.springangularblog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping
	public ResponseEntity createPost(@RequestBody PostDto postDto) {
		postService.createPost(postDto);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<PostDto>> showAllPosts() {
		return new ResponseEntity<List<PostDto>>(postService.showAllPosts(), HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id)  {
		ResponseEntity<PostDto>  resEty= new ResponseEntity<PostDto>(postService.readSinglePost(id), HttpStatus.OK);
		return resEty;
	}
}
