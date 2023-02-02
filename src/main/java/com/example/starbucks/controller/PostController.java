package com.example.starbucks.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.starbucks.services.PostService;
import com.example.starbucks.entities.Post;
import com.example.starbucks.requests.PostCreateRequest;
import com.example.starbucks.requests.PostUpdateRequest;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping
	public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
		return postService.getAllposts(userId);
	}
	
	@GetMapping("/{postId}")
	public Post getOnePost(@PathVariable Long postId) {
		return postService.getOnePostById(postId);
	}
	
	@PostMapping("/new")
	public String createPost(@RequestBody PostCreateRequest newPostRequest) {
		postService.createPost(newPostRequest);
		return "new post saved!";
	}
	
	@PutMapping("update/{postId}")
	public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost) {
		return postService.updateOnePostById(postId,updatePost);
	}
	
	@PostMapping("delete/{postId}")
	public String deleteOnePost(@PathVariable Long postId) {
		 postService.deleteOnePostById(postId);
		 return "post deleted!";
	}
}	
