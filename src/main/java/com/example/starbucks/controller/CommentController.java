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

import com.example.starbucks.entities.Comment;
import com.example.starbucks.entities.Post;
import com.example.starbucks.requests.CommentCreateRequest;
import com.example.starbucks.requests.CommentUpdateRequest;
import com.example.starbucks.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping
	public List<Comment> getAllComments(@RequestParam Optional<Long> userId,
			@RequestParam Optional<Long> postId){
		
		return commentService.getAllComments(userId,postId);
		
	}
	@GetMapping("/{commentId}")
	public Optional<Comment> getOneComment(@PathVariable Long commentId) {
		return commentService.getOneComment(commentId);
	}
	
	@PostMapping()
	public Comment createOneComment(@RequestBody CommentCreateRequest newPost) {
		return commentService.createComment(newPost);
	}
	
	@PutMapping("update/{commentId}")
	public Comment updateComment(@PathVariable Long commentId,
			@RequestBody CommentUpdateRequest updateComment) {
		return commentService.updateComment(commentId,updateComment);
	}
	
	@PostMapping("delete/{commentId}")
	public String deleteComment(@PathVariable Long commentId) {
		 commentService.deleteComment(commentId);
		 return "Comment deleted!";
	}
}
