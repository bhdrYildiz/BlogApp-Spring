package com.example.starbucks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.starbucks.entities.Like;
import com.example.starbucks.entities.Post;
import com.example.starbucks.entities.User;
import com.example.starbucks.repositories.LikeRepository;
import com.example.starbucks.requests.LikeCreateRequest;

@Service
public class LikeService {
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;

	public List<Like> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
		if(userId.isPresent() && postId.isPresent()) {
			return likeRepository.findByUserIdAndPostId(userId,postId);
		}
		else if(userId.isPresent()) {
			return likeRepository.findByUserId(userId);
		}
		else if(postId.isPresent()) {
			return likeRepository.findByPostId(postId);
		}else
		return likeRepository.findAll();
	}

	public Like createOneLike(LikeCreateRequest newLike) {
		User user = userService.getOneUser(newLike.getUserId());
		Post post = postService.getOnePostById(newLike.getPostId());
		
		if(user != null && post != null) {
			Like saveLike = new Like();
			saveLike.setId(newLike.getId());
			saveLike.setUser(user);
			saveLike.setPost(post);
			return likeRepository.save(saveLike);
		}else
			return null;
	}

	public Like getOneLike(Long likeId) {
		return likeRepository.findById(likeId).orElse(null);
	}

	public void deleteOneLike(Long likeId) {
		likeRepository.deleteById(likeId);
		
	}
	
}
