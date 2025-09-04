package com.quizapp.dao;

import java.util.List;

import com.quizapp.model.Question;
import com.quizapp.model.Quiz;

public interface QuizDAO {
    int createQuiz(Quiz quiz);
    List<Quiz> listQuizzes();
    int addQuestion(Question q);
    List<Question> getQuestionsByQuiz(int quizId);
}
