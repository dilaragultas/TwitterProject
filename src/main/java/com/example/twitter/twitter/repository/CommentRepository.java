package com.example.twitter.twitter.repository;

import com.example.twitter.twitter.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTwitId(Long twitId);
}