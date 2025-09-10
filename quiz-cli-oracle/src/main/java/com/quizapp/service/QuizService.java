package com.quizapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizapp.dao.QuizDAO;
import com.quizapp.model.Question;
import com.quizapp.model.Quiz;

@Service
public class QuizService {

    private final QuizDAO quizDAO;

    public QuizService(QuizDAO quizDAO) {
        this.quizDAO = quizDAO;
    }

    // Create Quiz
    public boolean createQuiz(String title, String description) {
        return quizDAO.createQuiz(new Quiz(0, title, description)) > 0;
    }

    // List all quizzes
    public List<Quiz> listQuizzes() {
        return quizDAO.listQuizzes();
    }

    // Add question to quiz
    public boolean addQuestion(Question q) {
        return quizDAO.addQuestion(q) > 0;
    }

    // Get questions for a quiz
    public List<Question> getQuestions(int quizId) {
        return quizDAO.getQuestionsByQuiz(quizId);
    }

    // ✅ Update an existing quiz
    public boolean updateQuiz(int quizId, String newTitle, String newDesc) {
        return quizDAO.updateQuiz(new Quiz(quizId, newTitle, newDesc)) > 0;
    }

    // ✅ Delete quiz by ID
    public boolean deleteQuiz(int quizId) {
        return quizDAO.deleteQuiz(quizId) > 0;
    }

    // ✅ Convenience method (alias for listQuizzes)
    public List<Quiz> getAllQuizzes() {
        return listQuizzes();
    }
}
