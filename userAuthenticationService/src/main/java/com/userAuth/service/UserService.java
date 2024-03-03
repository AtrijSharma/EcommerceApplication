package com.userAuth.service;

import com.userAuth.entity.User;

public interface UserService {
    void register(User user);

    String login(User user);
}

