package com.example.twitter.twitter.controller;

import com.example.twitter.twitter.dto.CreateTwitRequest;
import com.example.twitter.twitter.entity.Twit;
import com.example.twitter.twitter.service.TwitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TwitController {

    @Autowired
    private final TwitService twitService;

    public TwitController(TwitService twitService) {
        this.twitService = twitService;
    }

    @PostMapping
    public ResponseEntity<Twit> createTwit(@RequestBody CreateTwitRequest request){
        return ResponseEntity.ok(twitService.createTwit(request.getUserId(), request.getContent()));
    }

    @GetMapping("/findByUserId")
    public ResponseEntity<List<Twit>> getTwitsByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(twitService.getTwitsByUserId(userId));
    }

    @GetMapping("/findById")
    public ResponseEntity<Twit> getTwitById(@RequestParam Long id) {
        return ResponseEntity.ok(twitService.getTwitById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Twit> updateTwit(@PathVariable Long id,
                                             @RequestParam Long userId,
                                             @RequestParam String content) {
        return ResponseEntity.ok(twitService.updateTwit(id, userId, content));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTwit(@PathVariable Long id,
                                              @RequestParam Long userId) {
        twitService.deleteTwit(id, userId);
        return ResponseEntity.ok("Tweet deleted successfully");
    }
}
