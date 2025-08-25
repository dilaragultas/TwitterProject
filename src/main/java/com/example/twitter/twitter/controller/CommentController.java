package com.example.twitter.twitter.controller;

import com.example.twitter.twitter.entity.Comment;
import com.example.twitter.twitter.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestParam Long userId,
                                                 @RequestParam Long twitId,
                                                 @RequestParam String content) {
        return ResponseEntity.ok(commentService.createComment(userId, twitId, content));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id,
                                                 @RequestParam Long userId,
                                                 @RequestParam String content) {
        return ResponseEntity.ok(commentService.updateComment(id, userId, content));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id,
                                                @RequestParam Long userId,
                                                @RequestParam Long twitOwnerId) {
        commentService.deleteComment(id, userId, twitOwnerId);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
