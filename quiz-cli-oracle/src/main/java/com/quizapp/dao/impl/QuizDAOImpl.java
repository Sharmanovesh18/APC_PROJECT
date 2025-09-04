package com.quizapp.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quizapp.dao.QuizDAO;
import com.quizapp.model.Question;
import com.quizapp.model.Quiz;

@Repository
public class QuizDAOImpl implements QuizDAO {

    private final JdbcTemplate jdbc;

    public QuizDAOImpl(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    @Override
    public int createQuiz(Quiz quiz) {
        return jdbc.update("INSERT INTO quizzes (title, description) VALUES (?, ?)",
                quiz.getTitle(), quiz.getDescription());
    }

    @Override
    public List<Quiz> listQuizzes() {
        return jdbc.query(
            "SELECT quiz_id, title, description FROM quizzes ORDER BY quiz_id",
            (ResultSet rs, int row) -> new Quiz(
                rs.getInt("quiz_id"),
                rs.getString("title"),
                rs.getString("description")
            )
        );
    }

    @Override
    public int addQuestion(Question q) {
        return jdbc.update(
            "INSERT INTO questions (quiz_id, question_text, option_a, option_b, option_c, option_d, correct_option) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)",
            q.getQuizId(), q.getQuestionText(),
            q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD(),
            String.valueOf(q.getCorrectOption())
        );
    }

    @Override
    public List<Question> getQuestionsByQuiz(int quizId) {
        return jdbc.query(
            "SELECT question_id, quiz_id, question_text, option_a, option_b, option_c, option_d, correct_option " +
            "FROM questions WHERE quiz_id = ? ORDER BY question_id",
            (ResultSet rs, int row) -> {
                Question q = new Question();
                q.setQuestionId(rs.getInt("question_id"));
                q.setQuizId(rs.getInt("quiz_id"));
                q.setQuestionText(rs.getString("question_text"));
                q.setOptionA(rs.getString("option_a"));
                q.setOptionB(rs.getString("option_b"));
                q.setOptionC(rs.getString("option_c"));
                q.setOptionD(rs.getString("option_d"));
                q.setCorrectOption(rs.getString("correct_option").charAt(0));
                return q;
            }, quizId
        );
    }
}

