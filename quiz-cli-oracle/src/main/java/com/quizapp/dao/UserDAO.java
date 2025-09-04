package com.quizapp.dao;

import com.quizapp.model.User;

public interface UserDAO {
    User findByUsername(String username);
    int save(User user);
}

