package com.example.twitter.twitter.controller;

import com.example.twitter.twitter.entity.Twit;
import com.example.twitter.twitter.service.TwitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TwitController {

    private final TwitService twitService;

    public TwitController(TwitService twitService) {
        this.twitService = twitService;
    }

    @PostMapping
    public ResponseEntity<Twit> createTweet(@RequestParam Long userId, @RequestParam String content) {
        return ResponseEntity.ok(twitService.createTweet(userId, content));
    }

    @GetMapping("/findByUserId")
    public ResponseEntity<List<Twit>> getTweetsByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(twitService.getTweetsByUserId(userId));
    }

    @GetMapping("/findById")
    public ResponseEntity<Twit> getTweetById(@RequestParam Long id) {
        return ResponseEntity.ok(twitService.getTweetById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Twit> updateTweet(@PathVariable Long id,
                                             @RequestParam Long userId,
                                             @RequestParam String content) {
        return ResponseEntity.ok(twitService.updateTweet(id, userId, content));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTweet(@PathVariable Long id,
                                              @RequestParam Long userId) {
        twitService.deleteTweet(id, userId);
        return ResponseEntity.ok("Tweet deleted successfully");
    }
}
