package com.javalearning.spring_oauth.config;

import com.javalearning.spring_oauth.entity.UserInfo;
import com.javalearning.spring_oauth.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            UserInfo user = new UserInfo();
            user.setUsername("testuser");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole("USER");

            if (userInfoRepository.findByUsername(user.getUsername()) == null) {
                userInfoRepository.save(user);
            }
        };
    }
}
