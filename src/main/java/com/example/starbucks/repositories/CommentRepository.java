package com.example.starbucks.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.starbucks.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	List<Comment> findByUserIdAndPostId(Optional<Long> userId, Optional<Long> postId);

	List<Comment> findByUserId(Optional<Long> userId);
	
	List<Comment> findByPostId(Optional<Long> commentId);
	
}
