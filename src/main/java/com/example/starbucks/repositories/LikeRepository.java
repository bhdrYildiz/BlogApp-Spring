package com.example.starbucks.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.starbucks.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

	List<Like> findByUserIdAndPostId(Optional<Long> userId, Optional<Long> postId);

	List<Like> findByUserId(Optional<Long> userId);

	List<Like> findByPostId(Optional<Long> postId);

}
