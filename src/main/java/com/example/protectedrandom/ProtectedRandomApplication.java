package com.example.protectedrandom;

import com.example.protectedrandom.entity.User;
import com.example.protectedrandom.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootApplication
public class ProtectedRandomApplication implements InitializingBean {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(ProtectedRandomApplication.class, args);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        repository.save(new User("user", encoder.encode("pass")));
        repository.save(new User("admin", encoder.encode("pass")));
    }
}
