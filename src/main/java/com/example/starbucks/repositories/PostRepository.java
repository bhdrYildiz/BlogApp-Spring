package com.example.starbucks.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.starbucks.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	List<Post> findByUserId(Optional<Long> userId);

}
