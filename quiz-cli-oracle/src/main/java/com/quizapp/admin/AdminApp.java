package com.quizapp.admin;

import java.util.Scanner;

import com.quizapp.dao.QuestionDAO;
import com.quizapp.model.Question;

public class AdminApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QuestionDAO dao = new QuestionDAO();

        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add Question");
            System.out.println("2. Delete Question");
            System.out.println("3. List Questions");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter quizId: ");
                    int quizId = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter question: ");
                    String text = sc.nextLine();
                    System.out.print("Option A: ");
                    String a = sc.nextLine();
                    System.out.print("Option B: ");
                    String b = sc.nextLine();
                    System.out.print("Option C: ");
                    String c = sc.nextLine();
                    System.out.print("Option D: ");
                    String d = sc.nextLine();
                    System.out.print("Correct option (A/B/C/D): ");
                    char correct = sc.next().charAt(0);

                    Question q = new Question(0, quizId, text, a, b, c, d, correct);
                    dao.addQuestion(q);
                    System.out.println("✅ Question added!");
                }
                case 2 -> {
                    System.out.print("Enter Question ID to delete: ");
                    int id = sc.nextInt();
                    dao.removeQuestion(id);
                    System.out.println("❌ Question deleted!");
                }
                case 3 -> dao.getAllQuestions().forEach(q ->
                        System.out.println(q.getQuestionId() + ". " + q.getQuestionText()));
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
