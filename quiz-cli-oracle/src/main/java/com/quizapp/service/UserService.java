package com.quizapp.service;

import org.springframework.stereotype.Service;

import com.quizapp.dao.UserDAO;
import com.quizapp.model.User;

@Service
public class UserService {
    private final UserDAO userDAO;
    public UserService(UserDAO userDAO) { this.userDAO = userDAO; }

    public boolean register(String username, String password) {
        if (userDAO.findByUsername(username) != null) return false;
        User u = new User(0, username, password, "USER");
        return userDAO.save(u) > 0;
    }

    public User login(String username, String password) {
        User u = userDAO.findByUsername(username);
        if (u == null) return null;
        // For production, hash & verify. For demo, plain text:
        return u.getPassword().equals(password) ? u : null;
    }
}
