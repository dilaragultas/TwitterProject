package com.example.twitter.twitter.repository;

import com.example.twitter.twitter.entity.Twit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TwitRepository extends JpaRepository<Twit, Long> {
    List<Twit> findByUserId(Long userId);
}