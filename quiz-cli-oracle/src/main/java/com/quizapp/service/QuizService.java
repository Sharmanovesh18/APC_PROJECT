package com.quizapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizapp.dao.QuizDAO;
import com.quizapp.model.Question;
import com.quizapp.model.Quiz;

@Service
public class QuizService {
    private final QuizDAO quizDAO;
    public QuizService(QuizDAO quizDAO) { this.quizDAO = quizDAO; }

    public boolean createQuiz(String title, String description) {
        return quizDAO.createQuiz(new Quiz(0, title, description)) > 0;
    }

    public List<Quiz> listQuizzes() { return quizDAO.listQuizzes(); }

    public boolean addQuestion(Question q) { return quizDAO.addQuestion(q) > 0; }

    public List<Question> getQuestions(int quizId) { return quizDAO.getQuestionsByQuiz(quizId); }
}
