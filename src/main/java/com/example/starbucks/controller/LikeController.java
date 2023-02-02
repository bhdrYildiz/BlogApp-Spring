package com.example.starbucks.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.starbucks.entities.Like;
import com.example.starbucks.repositories.LikeRepository;
import com.example.starbucks.requests.LikeCreateRequest;
import com.example.starbucks.services.LikeService;

@RestController
@RequestMapping("/likes")
public class LikeController {

	@Autowired
	private LikeService likeService;
	
	@GetMapping
	public List<Like> getAllLikes(@RequestParam Optional<Long> userId,
			@RequestParam Optional<Long>  postId){
		return likeService.getAllLikes(userId,postId);
	}
	
	@PostMapping("new")
	public Like createLike(@RequestBody LikeCreateRequest newLike) {
		return likeService.createOneLike(newLike);
	}
	@GetMapping("/{likeId}")
	public Like getOneLike(@PathVariable Long likeId) {
		return likeService.getOneLike(likeId);
	}
	
	@PostMapping("delete/{likeId}")
	public String deleteOneLike(@PathVariable Long likeId) {
		likeService.deleteOneLike(likeId);
		return "like deleted!";
	}
	
	
}
