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

    public TwitService(TwitRepository twitRepository, UserRepository userRepository) {
        this.twitRepository = twitRepository;
        this.userRepository = userRepository;
    }

    public Twit createTwit(Long userId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Twit twit = new Twit();
        twit.setContent(content);
        twit.setUser(user);

        return twitRepository.save(twit);
    }

    public List<Twit> getTwitsByUserId(Long userId) {
        return twitRepository.findByUserId(userId);
    }

    public Twit getTwitById(Long id) {
        return twitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));
    }

    public Twit updateTwit(Long twitId, Long userId, String newContent) {
        Twit twit = getTwitById(twitId);
        if (!twit.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not the owner of this tweet");
        }
        twit.setContent(newContent);
        return twitRepository.save(twit);
    }

    public void deleteTwit(Long twitId, Long userId) {
        Twit twit = getTwitById(twitId);
        if (!twit.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to delete this tweet");
        }
        twitRepository.delete(twit);
    }
}
