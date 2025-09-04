package com.quizapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.quizapp.config.HibernateUtil;
import com.quizapp.model.Question;

public class QuestionDAO {

    public void addQuestion(Question question) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(question);
            tx.commit();
        }
    }

    public void removeQuestion(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Question q = session.get(Question.class, id);
            if (q != null) session.delete(q);
            tx.commit();
        }
    }

    public List<Question> getAllQuestionsOrdered() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Question q ORDER BY q.quizOrder", Question.class).list();
        }
    }

    public List<Question> getAllQuestions() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Question", Question.class).list();
        }
    }
}
