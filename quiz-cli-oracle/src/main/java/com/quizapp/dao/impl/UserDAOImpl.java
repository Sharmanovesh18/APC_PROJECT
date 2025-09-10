package com.quizapp.dao.impl;

import java.sql.ResultSet;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quizapp.dao.UserDAO;
import com.quizapp.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbc;

    public UserDAOImpl(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    @Override
    public User findByUsername(String username) {
        try {
            return jdbc.queryForObject(
                "SELECT user_id, username, password, role FROM users WHERE UPPER(username)=UPPER(?)",
                (ResultSet rs, int row) -> {
                    User u = new User();
                    u.setUserId(rs.getInt("user_id"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                    u.setRole(rs.getString("role"));
                    return u;
                }, username
            );
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public int save(User user) {
        return jdbc.update(
            "INSERT INTO users (username, password, role) VALUES (?, ?, ?)",
            user.getUsername(), user.getPassword(), user.getRole()
        );
    }
}
