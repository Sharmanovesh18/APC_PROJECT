package com.quizapp.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quizapp.dao.ResultDAO;
import com.quizapp.model.Result;

@Repository
public class ResultDAOImpl implements ResultDAO {

    private final JdbcTemplate jdbc;

    public ResultDAOImpl(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    @Override
    public int saveResult(Result r) {
        return jdbc.update(
            "INSERT INTO results (user_id, quiz_id, score) VALUES (?, ?, ?)",
            r.getUserId(), r.getQuizId(), r.getScore()
        );
    }

    @Override
    public List<Result> getResultsByUser(int userId) {
        return jdbc.query(
            "SELECT result_id, user_id, quiz_id, score, attempt_date FROM results " +
            "WHERE user_id = ? ORDER BY attempt_date DESC",
            (ResultSet rs, int row) -> new Result(
                rs.getInt("result_id"),
                rs.getInt("user_id"),
                rs.getInt("quiz_id"),
                rs.getInt("score"),
                rs.getTimestamp("attempt_date").toLocalDateTime()
            ),
            userId
        );
    }
}
