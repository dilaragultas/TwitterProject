package com.example.twitter.twitter.controller;

import com.example.twitter.twitter.dto.DislikeRequest;
import com.example.twitter.twitter.dto.LikeTwitRequest;
import com.example.twitter.twitter.entity.Like;
import com.example.twitter.twitter.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<Like> likeTwit(@RequestBody LikeTwitRequest request){
        return ResponseEntity.ok(likeService.likeTwit(request.getUserId(), request.getTwitId()));
    }

    @PostMapping("/dislike")
    public ResponseEntity<String> dislikeTwit(@RequestBody DislikeRequest request) {
        likeService.dislikeTwit(request.getUserId(), request.getTwitId());
        return ResponseEntity.ok("Like removed");
    }
}
