package com.example.starbucks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.starbucks.entities.Post;
import com.example.starbucks.entities.User;
import com.example.starbucks.repositories.PostRepository;
import com.example.starbucks.requests.PostCreateRequest;
import com.example.starbucks.requests.PostUpdateRequest;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserService userService;

	public List<Post> getAllposts(Optional<Long> userId) {
		if(userId.isPresent()) {
			return postRepository.findByUserId(userId);		
		}else
		return postRepository.findAll();
	}

	public Post getOnePostById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}

	public Post createPost(PostCreateRequest newPostRequest) {
		User user = userService.getOneUser(newPostRequest.getUserId());
		if(user == null) {
			return null;
		}
		Post toSave = new Post();
		toSave.setId(newPostRequest.getId());
		toSave.setText(newPostRequest.getText());
		toSave.setTitle(newPostRequest.getTitle());
		toSave.setUser(user);
		return postRepository.save(toSave);	
	}

	public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
		Optional<Post> post = postRepository.findById(postId);
		if(post.isPresent()) {
			Post toUpdate = post.get();
			toUpdate.setText(updatePost.getText());
			toUpdate.setTitle(updatePost.getTitle());
			postRepository.save(toUpdate);
			return toUpdate;
		}
		return null;	
	}

	public String deleteOnePostById(Long postId) {
		postRepository.deleteById(postId);
		return "Post deleted!";
	}

}
