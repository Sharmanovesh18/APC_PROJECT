package com.quizapp.dao;

import java.util.List;

import com.quizapp.model.Result;

public interface ResultDAO {
    int saveResult(Result r);
    List<Result> getResultsByUser(int userId);
}
