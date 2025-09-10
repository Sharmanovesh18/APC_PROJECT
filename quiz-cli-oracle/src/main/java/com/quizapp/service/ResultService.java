package com.quizapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizapp.dao.ResultDAO;
import com.quizapp.model.Question;
import com.quizapp.model.Result;

@Service
public class ResultService {
    private final ResultDAO resultDAO;
    public ResultService(ResultDAO resultDAO) { this.resultDAO = resultDAO; }

    public int evaluate(List<Question> questions, List<Character> answers) {
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            char correct = Character.toUpperCase(questions.get(i).getCorrectOption());
            char given   = Character.toUpperCase(answers.get(i));
            if (correct == given) score++;
        }
        return score;
    }

    public boolean saveResult(int userId, int quizId, int score) {
        return resultDAO.saveResult(new Result(0, userId, quizId, score, null)) > 0;
    }
}
