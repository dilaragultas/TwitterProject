package com.example.twitter.twitter.service;

import com.example.twitter.twitter.entity.Like;
import com.example.twitter.twitter.entity.Twit;
import com.example.twitter.twitter.entity.User;
import com.example.twitter.twitter.repository.LikeRepository;
import com.example.twitter.twitter.repository.TwitRepository;
import com.example.twitter.twitter.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final TwitRepository twitRepository;
    private final UserRepository userRepository;

    public LikeService(LikeRepository likeRepository, TwitRepository twitRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.twitRepository = twitRepository;
        this.userRepository = userRepository;
    }

    public Like likeTwit(Long userId, Long twitId) {
        if (likeRepository.findByUserIdAndTwitId(userId, twitId).isPresent()) {
            throw new RuntimeException("Already liked");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Twit twit = twitRepository.findById(twitId)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));

        Like like = new Like();
        like.setUser(user);
        like.setTwit(twit);
        return likeRepository.save(like);
    }

    public void dislikeTwit(Long userId, Long twitId) {
        Like like = likeRepository.findByUserIdAndTwitId(userId, twitId)
                .orElseThrow(() -> new RuntimeException("Like not found"));
        likeRepository.delete(like);
    }
}
