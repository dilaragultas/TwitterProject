package com.example.twitter.twitter.service;

import com.example.twitter.twitter.entity.Twit;
import com.example.twitter.twitter.entity.User;
import com.example.twitter.twitter.repository.TwitRepository;
import com.example.twitter.twitter.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitService {

    private final TwitRepository twitRepository;
    private final UserRepository userRepository;

    public TwitService(TwitRepository tweetRepository, UserRepository userRepository) {
        this.twitRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public Twit createTweet(Long userId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Twit tweet = new Twit();
        tweet.setContent(content);
        tweet.setUser(user);

        return twitRepository.save(tweet);
    }

    public List<Twit> getTweetsByUserId(Long userId) {
        return twitRepository.findByUserId(userId);
    }

    public Twit getTweetById(Long id) {
        return twitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));
    }

    public Twit updateTweet(Long tweetId, Long userId, String newContent) {
        Twit tweet = getTweetById(tweetId);
        if (!tweet.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not the owner of this tweet");
        }
        tweet.setContent(newContent);
        return twitRepository.save(tweet);
    }

    public void deleteTweet(Long tweetId, Long userId) {
        Twit tweet = getTweetById(tweetId);
        if (!tweet.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to delete this tweet");
        }
        twitRepository.delete(tweet);
    }
}
