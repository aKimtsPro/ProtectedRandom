package com.example.protectedrandom.service;

import com.example.protectedrandom.entity.User;
import com.example.protectedrandom.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class NumberService {

    private final UserRepository userRepository;

    public NumberService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long generateFor(String username){

        long rslt = new Random().nextLong();

        userRepository.findByUsername(username)
                .ifPresent( u -> {
                    u.getGenerated().add(rslt);
                    userRepository.save(u);
                } );

        return rslt;
    }

    public List<Long> getGeneratedBy(String name) {
        return userRepository.findByUsername(name)
                .map(User::getGenerated)
                .orElseThrow();
    }
}
