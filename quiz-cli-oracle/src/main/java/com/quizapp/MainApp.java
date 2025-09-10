package com.quizapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.quizapp.config.DataSourceConfig;
import com.quizapp.model.Question;
import com.quizapp.model.Quiz;
import com.quizapp.model.User;
import com.quizapp.service.QuizService;
import com.quizapp.service.ResultService;
import com.quizapp.service.UserService;

public class MainApp {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataSourceConfig.class)) {
            UserService userService = ctx.getBean(UserService.class);
            QuizService quizService = ctx.getBean(QuizService.class);
            ResultService resultService = ctx.getBean(ResultService.class);

            while (true) {
                System.out.println("\n=== QUIZ APP ===");
                System.out.println("1) Register");
                System.out.println("2) Login");
                System.out.println("3) Exit");
                System.out.print("Choose: ");
                String ch = sc.nextLine().trim();

                switch (ch) {
                    case "1":
                        System.out.print("Username: ");
                        String ru = sc.nextLine().trim();
                        System.out.print("Password: ");
                        String rp = sc.nextLine().trim();
                        boolean ok = userService.register(ru, rp);
                        System.out.println(ok ? "✅ Registered successfully!" : "❌ Username already exists.");
                        break;
                    case "2":
                        System.out.print("Username: ");
                        String u = sc.nextLine().trim();
                        System.out.print("Password: ");
                        String p = sc.nextLine().trim();
                        User logged = userService.login(u, p);
                        if (logged == null) {
                            System.out.println("❌ Invalid credentials.");
                        } else {
                            if ("ADMIN".equalsIgnoreCase(logged.getRole())) {
                                adminMenu(quizService);
                            } else {
                                userMenu(quizService, resultService, logged);
                            }
                        }
                        break;
                    case "3":
                        System.out.println("Bye!");
                        return;
                    default:
                        System.out.println("❌ Invalid choice.");
                }
            }
        }
    }

    // ✅ SINGLE CLEAN ADMIN MENU
    private static void adminMenu(QuizService quizService) {
        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1) Add Quiz");
            System.out.println("2) Update Quiz");
            System.out.println("3) Delete Quiz");
            System.out.println("4) View All Quizzes");
            System.out.println("5) Add Question to Quiz");
            System.out.println("6) Back");
            System.out.print("Choose: ");
            String ch = sc.nextLine().trim();

            switch (ch) {
                case "1": // Add Quiz
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    System.out.println(quizService.createQuiz(title, desc) ? "✅ Quiz added." : "❌ Failed.");
                    break;

                case "2": // Update Quiz
                    listQuizzes(quizService);
                    System.out.print("Enter quiz ID to update: ");
                    int uqid = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter new title: ");
                    String newTitle = sc.nextLine();
                    System.out.print("Enter new description: ");
                    String newDesc = sc.nextLine();
                    System.out.println(quizService.updateQuiz(uqid, newTitle, newDesc) ? "✅ Quiz updated." : "❌ Failed.");
                    break;

                case "3": // Delete Quiz
                    listQuizzes(quizService);
                    System.out.print("Enter quiz ID to delete: ");
                    int delId = Integer.parseInt(sc.nextLine());
                    System.out.println(quizService.deleteQuiz(delId) ? "✅ Quiz deleted." : "❌ Failed.");
                    break;

                case "4": // View Quizzes
                    listQuizzes(quizService);
                    break;

                case "5": // Add Question
                    listQuizzes(quizService);
                    System.out.print("Enter quizId: ");
                    int qid = Integer.parseInt(sc.nextLine().trim());
                    Question q = new Question();
                    q.setQuizId(qid);
                    System.out.print("Question text: ");
                    q.setQuestionText(sc.nextLine());
                    System.out.print("Option A: "); q.setOptionA(sc.nextLine());
                    System.out.print("Option B: "); q.setOptionB(sc.nextLine());
                    System.out.print("Option C: "); q.setOptionC(sc.nextLine());
                    System.out.print("Option D: "); q.setOptionD(sc.nextLine());
                    System.out.print("Correct option (A/B/C/D): ");
                    q.setCorrectOption(sc.nextLine().trim().toUpperCase().charAt(0));
                    System.out.println(quizService.addQuestion(q) ? "✅ Question added." : "❌ Failed.");
                    break;

                case "6": // Back
                    return;

                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void userMenu(QuizService quizService, ResultService resultService, User user) {
        while (true) {
            System.out.println("\n--- USER MENU ---");
            System.out.println("1) Take Quiz");
            System.out.println("2) Back");
            System.out.print("Choose: ");
            String ch = sc.nextLine().trim();

            if ("1".equals(ch)) {
                listQuizzes(quizService);
                System.out.print("Enter quizId to take: ");
                int qid = Integer.parseInt(sc.nextLine().trim());
                List<Question> qs = quizService.getQuestions(qid);
                if (qs.isEmpty()) {
                    System.out.println("No questions in this quiz yet.");
                    continue;
                }
                List<Character> answers = new ArrayList<>();
                int idx = 1;
                for (Question qu : qs) {
                    System.out.println("\nQ" + (idx++) + ") " + qu.getQuestionText());
                    System.out.println("  A) " + qu.getOptionA());
                    System.out.println("  B) " + qu.getOptionB());
                    System.out.println("  C) " + qu.getOptionC());
                    System.out.println("  D) " + qu.getOptionD());
                    char ans;
                    while (true) {
                        System.out.print("Your answer (A/B/C/D): ");
                        String in = sc.nextLine().trim().toUpperCase();
                        if (in.matches("[ABCD]")) { ans = in.charAt(0); break; }
                        System.out.println("Please enter A/B/C/D.");
                    }
                    answers.add(ans);
                }
                int score = resultService.evaluate(qs, answers);
                boolean saved = resultService.saveResult(user.getUserId(), qid, score);
                System.out.println("\nYour Score: " + score + " / " + qs.size() + (saved ? " (saved)" : " (not saved)"));
            } else if ("2".equals(ch)) {
                return;
            } else {
                System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void listQuizzes(QuizService quizService) {
        List<Quiz> list = quizService.listQuizzes();
        if (list.isEmpty()) {
            System.out.println("No quizzes yet.");
        } else {
            System.out.println("\nAvailable Quizzes:");
            list.forEach(q -> System.out.println("  " + q.getQuizId() + ") " + q.getTitle() +
                    (q.getDescription() == null ? "" : " - " + q.getDescription())));
        }
    }
}
