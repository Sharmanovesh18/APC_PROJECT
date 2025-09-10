package com.quizapp.dao;

import java.util.List;

import com.quizapp.model.Question;
import com.quizapp.model.Quiz;

public interface QuizDAO {
    
    // ✅ Quiz management
    int createQuiz(Quiz quiz);
    List<Quiz> listQuizzes();
    int updateQuiz(Quiz quiz);       // update title/description
    int deleteQuiz(int quizId);      // delete a quiz (also deletes its questions)

    // ✅ Question management
    int addQuestion(Question q);
    List<Question> getQuestionsByQuiz(int quizId);
    int updateQuestion(Question q);  // update question text/options/correct answer
    int deleteQuestion(int questionId); // delete specific question
}
