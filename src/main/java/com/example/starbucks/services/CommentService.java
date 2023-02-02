package com.example.starbucks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.starbucks.entities.Comment;
import com.example.starbucks.entities.Post;
import com.example.starbucks.entities.User;
import com.example.starbucks.repositories.CommentRepository;
import com.example.starbucks.requests.CommentCreateRequest;
import com.example.starbucks.requests.CommentUpdateRequest;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;

	public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {
		if(userId.isPresent() && postId.isPresent()) {
			return commentRepository.findByUserIdAndPostId(userId,postId);
		}
		else if(userId.isPresent()) {
			return commentRepository.findByUserId(userId);
		}
		else if(postId.isPresent()) {
			return commentRepository.findByPostId(postId);
		}
		else
			return commentRepository.findAll();
		}

	public Optional<Comment> getOneComment(Long commentId) {
		return commentRepository.findById(commentId);
	}

	public Comment createComment(CommentCreateRequest newPost) {
		
		User user = userService.getOneUser(newPost.getUserId());
		Post post = postService.getOnePostById(newPost.getPostId());
		if(user != null && post != null) {
			Comment saveComment = new Comment();
			saveComment.setId(newPost.getId());
			saveComment.setPost(post);
			saveComment.setUser(user);
			saveComment.setText(newPost.getText());
			return commentRepository.save(saveComment);
		}else
			return null;
	}

	public Comment updateComment(Long commentId, CommentUpdateRequest updateComment) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if(comment.isPresent()) {
			Comment toUpdate = comment.get();
			toUpdate.setText(updateComment.getText());
			commentRepository.save(toUpdate);
			return toUpdate;
		}else
			return null;
	}

	public String deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
		return "Comment is deleted!";
		
	}

	
}
