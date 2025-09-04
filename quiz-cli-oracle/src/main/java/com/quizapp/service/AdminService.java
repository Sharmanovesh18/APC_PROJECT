package com.quizapp.service;

import com.quizapp.dao.QuestionDAO;
import com.quizapp.model.Question;
import java.util.List;

public class AdminService {
    private QuestionDAO questionDAO = new QuestionDAO();

    public void addQuestion(String text, String a, String b, String c, String d, String correct, int order) {
        Question q = new Question();
        q.setQuestionText(text);
        q.setOptionA(a);
        q.setOptionB(b);
        q.setOptionC(c);
        q.setOptionD(d);
        q.setCorrectAnswer(correct);
        q.setQuizOrder(order);
        questionDAO.addQuestion(q);
    }

    public void removeQuestion(int id) {
        questionDAO.removeQuestion(id);
    }

    public List<Question> listQuestions() {
        return questionDAO.getAllQuestionsOrdered();
    }
}
