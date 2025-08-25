package com.example.twitter.twitter.service;

import com.example.twitter.twitter.entity.Comment;
import com.example.twitter.twitter.entity.Twit;
import com.example.twitter.twitter.entity.User;
import com.example.twitter.twitter.repository.CommentRepository;
import com.example.twitter.twitter.repository.TwitRepository;
import com.example.twitter.twitter.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TwitRepository twitRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, TwitRepository twitRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.twitRepository = twitRepository;
        this.userRepository = userRepository;
    }

    public Comment createComment(Long userId, Long twitId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Twit twit = twitRepository.findById(twitId)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setTwit(twit);

        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, Long userId, String newContent) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to edit this comment");
        }
        comment.setContent(newContent);
        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, Long userId, Long twitOwnerId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(userId) && !twitOwnerId.equals(userId)) {
            throw new RuntimeException("Not allowed to delete this comment");
        }
        commentRepository.delete(comment);
    }
}