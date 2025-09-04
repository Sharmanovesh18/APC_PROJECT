
package com.quizapp.model;

import javax.persistence.*;  // use javax.persistence for JPA 2.x (matches pom.xml)

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment in MySQL
    private int questionId;

    @Column(nullable = false)
    private int quizId;   // FK → quiz table (can later map with @ManyToOne)

    @Column(nullable = false, length = 500)
    private String questionText;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    @Column(nullable = false, length = 1)
    private char correctOption; // 'A'/'B'/'C'/'D'
    private String correctAnswer; // for AdminService compatibility
    private int quizOrder; // for AdminService compatibility

    // --- constructors ---
    public Question() {}

    public Question(int questionId, int quizId, String questionText,
                    String optionA, String optionB, String optionC,
                    String optionD, char correctOption) {
        this.questionId = questionId;
        this.quizId = quizId;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
    }

    // --- getters & setters ---
    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }

    public int getQuizId() { return quizId; }
    public void setQuizId(int quizId) { this.quizId = quizId; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getOptionA() { return optionA; }
    public void setOptionA(String optionA) { this.optionA = optionA; }

    public String getOptionB() { return optionB; }
    public void setOptionB(String optionB) { this.optionB = optionB; }

    public String getOptionC() { return optionC; }
    public void setOptionC(String optionC) { this.optionC = optionC; }

    public String getOptionD() { return optionD; }
    public void setOptionD(String optionD) { this.optionD = optionD; }

    public char getCorrectOption() { return correctOption; }
    public void setCorrectOption(char correctOption) { this.correctOption = correctOption; }

    // --- added for AdminService compatibility ---
    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public int getQuizOrder() { return quizOrder; }
    public void setQuizOrder(int quizOrder) { this.quizOrder = quizOrder; }
}
