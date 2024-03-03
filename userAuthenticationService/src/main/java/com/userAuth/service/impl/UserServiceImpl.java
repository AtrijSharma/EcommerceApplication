package com.userAuth.service.impl;


import com.userAuth.entity.User;
import com.userAuth.repository.UserRepository;
import com.userAuth.service.UserService;
import com.userAuth.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        // Save user details to database
        logger.info("Registering user: {}", user.getUsername());
        userRepository.save(user);
    }

    @Override
    public String login(User user) {
        // Validate user credentials
        logger.info("Logging in user: {}", user.getUsername());
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && Objects.equals(existingUser.getPassword(), user.getPassword())) {
            // Generate JWT token
            return JWTUtil.generateToken(existingUser);
        } else {
            logger.warn("Invalid username or password: {}", user.getUsername());
            return "Invalid username or password";
        }
    }
}

