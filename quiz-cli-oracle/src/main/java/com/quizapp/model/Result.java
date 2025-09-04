package com.quizapp.model;

import java.time.LocalDateTime;

public class Result {
    private int resultId;
    private int userId;
    private int quizId;
    private int score;
    private LocalDateTime attemptDate;

    public Result() {}

    public Result(int resultId, int userId, int quizId, int score, LocalDateTime attemptDate) {
        this.resultId = resultId; this.userId = userId; this.quizId = quizId;
        this.score = score; this.attemptDate = attemptDate;
    }

    public int getResultId() { return resultId; }
    public void setResultId(int resultId) { this.resultId = resultId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getQuizId() { return quizId; }
    public void setQuizId(int quizId) { this.quizId = quizId; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public LocalDateTime getAttemptDate() { return attemptDate; }
    public void setAttemptDate(LocalDateTime attemptDate) { this.attemptDate = attemptDate; }
}
