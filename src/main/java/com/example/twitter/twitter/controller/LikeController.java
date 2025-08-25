package com.example.twitter.twitter.controller;

import com.example.twitter.twitter.entity.Like;
import com.example.twitter.twitter.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<Like> likeTwit(@RequestParam Long userId, @RequestParam Long twitId) {
        return ResponseEntity.ok(likeService.likeTwit(userId, twitId));
    }

    @PostMapping("/dislike")
    public ResponseEntity<String> dislikeTwit(@RequestParam Long userId, @RequestParam Long twitId) {
        likeService.dislikeTwit(userId, twitId);
        return ResponseEntity.ok("Like removed");
    }
}
