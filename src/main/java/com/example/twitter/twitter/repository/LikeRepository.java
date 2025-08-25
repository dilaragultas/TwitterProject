package com.example.twitter.twitter.repository;

import com.example.twitter.twitter.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndTwitId(Long userId, Long twitId);
}
