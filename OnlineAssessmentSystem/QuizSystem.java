import java.io.*;
import java.util.*;

public class QuizSystem {


    static class Question {
        String text;
        String optionA;
        String optionB;
        String optionC;
        String optionD;
        String correctAnswer;

        Question(String text, String a, String b, String c, String d, String correct) {
            this.text          = text;
            this.optionA       = a;
            this.optionB       = b;
            this.optionC       = c;
            this.optionD       = d;
            this.correctAnswer = correct.toUpperCase();
        }

        void display(int number) {
            System.out.println("\n  Q" + number + ". " + text);
            System.out.println("     A) " + optionA);
            System.out.println("     B) " + optionB);
            System.out.println("     C) " + optionC);
            System.out.println("     D) " + optionD);
        }
    }


    static class QuestionBank {
        ArrayList<Question> questions = new ArrayList<>();
        String filename = "questions.txt";

        QuestionBank() {
            loadFromFile();
        }

        void addQuestion(Question q) {
            questions.add(q);
            saveToFile();
        }

        void saveToFile() {
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(filename));
                for (Question q : questions) {
                    writer.println(q.text + "|" + q.optionA + "|" + q.optionB + "|"
                                 + q.optionC + "|" + q.optionD + "|" + q.correctAnswer);
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("  Could not save questions.");
            }
        }

        void loadFromFile() {
            File file = new File(filename);
            if (!file.exists()) return;
            try {
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine().trim();
                    if (line.isEmpty()) continue;
                    String[] parts = line.split("\\|");
                    if (parts.length == 6) {
                        questions.add(new Question(parts[0], parts[1], parts[2],
                                                   parts[3], parts[4], parts[5]));
                    }
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("  Could not load questions.");
            }
        }

        boolean isEmpty() {
            return questions.isEmpty();
        }

        int size() {
            return questions.size();
        }
    }


    static class QuizTimer {
        long startTime;
        long endTime;

        void start() {
            startTime = System.currentTimeMillis();
        }

        void stop() {
            endTime = System.currentTimeMillis();
        }

        int secondsTaken() {
            return (int) ((endTime - startTime) / 1000);
        }

        String formatted() {
            int secs = secondsTaken();
            int mins = secs / 60;
            int rem  = secs % 60;
            if (mins > 0) return mins + " min " + rem + " sec";
            return rem + " seconds";
        }
    }


    static class Result {
        String studentName;
        int score;
        int total;
        String timeTaken;
        ArrayList<String> wrongAnswers = new ArrayList<>();

        Result(String name, int score, int total, String time) {
            this.studentName = name;
            this.score       = score;
            this.total       = total;
            this.timeTaken   = time;
        }

        double percentage() {
            return (score * 100.0) / total;
        }

        String grade() {
            double p = percentage();
            if (p >= 90) return "A+ Outstanding!";
            if (p >= 75) return "A  Excellent!";
            if (p >= 60) return "B  Good job!";
            if (p >= 40) return "C  Needs more practice.";
            return "D  Keep trying — you got this!";
        }

        void print() {
            System.out.println("Quiz Result Summary");
            System.out.printf ("Student   : %-32s%n", studentName);
            System.out.printf ("Score     : %d / %d%-29s%n", score, total, "");
            System.out.printf ("Percentage: %.1f%%%-30s%n", percentage(), "");
            System.out.printf ("Grade     : %-32s%n", grade());
            System.out.printf ("Time Taken: %-32s%n", timeTaken);

            if (wrongAnswers.isEmpty()) {
                System.out.println("Perfect score — all answers correct!");
            } else {
                System.out.println("Review these questions:");
                for (String w : wrongAnswers) {
                    System.out.printf("%-40s %n", w);
                }
            }
        }
    }


    static Scanner sc      = new Scanner(System.in);
    static QuestionBank bank = new QuestionBank();
    static int QUIZ_TIME_LIMIT = 300;


    static void adminMenu() {
        System.out.print("\n  Enter admin password: ");
        String pass = sc.nextLine().trim();
        if (!pass.equals("admin123")) {
            System.out.println("  Wrong password.");
            return;
        }

        while (true) {
            System.out.println("\n Admin Panel ");
            System.out.println("    1. Add a question");
            System.out.println("    2. View all questions");
            System.out.println("    3. Set quiz time limit");
            System.out.println("    4. Back to main menu");
            System.out.print("  Choice: ");
            String choice = sc.nextLine().trim();

            if (choice.equals("1"))      addQuestion();
            else if (choice.equals("2")) viewQuestions();
            else if (choice.equals("3")) setTimeLimit();
            else if (choice.equals("4")) break;
            else System.out.println("  Please pick 1–4.");
        }
    }


    static void addQuestion() {
        System.out.println("\n Add New Question");
        System.out.print("  Question text  : ");
        String text = sc.nextLine().trim();

        System.out.print("  Option A       : ");
        String a = sc.nextLine().trim();

        System.out.print("  Option B       : ");
        String b = sc.nextLine().trim();

        System.out.print("  Option C       : ");
        String c = sc.nextLine().trim();

        System.out.print("  Option D       : ");
        String d = sc.nextLine().trim();

        String correct = "";
        while (!correct.matches("[AaBbCcDd]")) {
            System.out.print("  Correct answer (A/B/C/D): ");
            correct = sc.nextLine().trim();
            if (!correct.matches("[AaBbCcDd]"))
                System.out.println("  Please enter A, B, C or D only.");
        }

        bank.addQuestion(new Question(text, a, b, c, d, correct));
        System.out.println(" Question saved! Total questions: " + bank.size());
    }


    static void viewQuestions() {
        if (bank.isEmpty()) {
            System.out.println("  No questions added yet.");
            return;
        }
        System.out.println("\n  All Questions (" + bank.size() + " total):");
        System.out.println("  " + "-".repeat(50));
        int num = 1;
        for (Question q : bank.questions) {
            System.out.println("  " + num + ". " + q.text + "  [Answer: " + q.correctAnswer + "]");
            num++;
        }
    }


    static void setTimeLimit() {
        System.out.print("  Enter time limit in seconds (current: " + QUIZ_TIME_LIMIT + "): ");
        try {
            int t = Integer.parseInt(sc.nextLine().trim());
            if (t > 0) {
                QUIZ_TIME_LIMIT = t;
                System.out.println("  Time limit set to " + t + " seconds.");
            } else {
                System.out.println("  Must be greater than 0.");
            }
        } catch (NumberFormatException e) {
            System.out.println("  Enter a valid number.");
        }
    }


    static void startQuiz() {
        if (bank.isEmpty()) {
            System.out.println("\n  No questions available. Ask admin to add questions first.");
            return;
        }

        System.out.print("\n  Enter your name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) name = "Student";

        System.out.println("\n Quiz Rules");
        System.out.println("  Total Questions : " + bank.size());
        System.out.println("  Time Limit      : " + QUIZ_TIME_LIMIT + " seconds");
        System.out.println("  Each question   : 1 mark");
        System.out.println("  No negative marking");
        System.out.println("\n  Press Enter to begin...");
        sc.nextLine();

        QuizTimer timer = new QuizTimer();
        timer.start();

        int score = 0;
        Result result = new Result(name, 0, bank.size(), "");
        int questionNumber = 1;

        for (Question q : bank.questions) {
            int elapsed = (int)((System.currentTimeMillis() - timer.startTime) / 1000);
            int remaining = QUIZ_TIME_LIMIT - elapsed;

            if (remaining <= 0) {
                System.out.println("\n  Time's up!");
                break;
            }

            System.out.println("\n Time remaining: " + remaining + " seconds");
            q.display(questionNumber);

            String answer = "";
            while (!answer.matches("[AaBbCcDd]")) {
                System.out.print("  Your answer (A/B/C/D): ");
                answer = sc.nextLine().trim();
                if (!answer.matches("[AaBbCcDd]"))
                    System.out.println("  Please type A, B, C or D.");
            }

            if (answer.equalsIgnoreCase(q.correctAnswer)) {
                score++;
                System.out.println(" Correct!");
            } else {
                System.out.println(" Wrong! Correct answer was: " + q.correctAnswer);
                result.wrongAnswers.add("Q" + questionNumber + ": " + q.text);
            }

            questionNumber++;
        }

        timer.stop();
        result.score     = score;
        result.timeTaken = timer.formatted();
        result.print();
    }


    static void loadDefaultQuestions() {
        if (!bank.isEmpty()) return;

        bank.addQuestion(new Question(
            "What is the full form of OOP?",
            "Object Oriented Programming",
            "Object Oriented Process",
            "Ordered Object Programming",
            "Online Object Programming",
            "A"
        ));
        bank.addQuestion(new Question(
            "Which of these is NOT a Java primitive type?",
            "int",
            "boolean",
            "String",
            "double",
            "C"
        ));
        bank.addQuestion(new Question(
            "What does JVM stand for?",
            "Java Variable Machine",
            "Java Virtual Machine",
            "Java Verified Module",
            "Just Virtual Method",
            "B"
        ));
        bank.addQuestion(new Question(
            "Which keyword is used to inherit a class in Java?",
            "implements",
            "super",
            "this",
            "extends",
            "D"
        ));
        bank.addQuestion(new Question(
            "What is the default value of an int variable in Java?",
            "null",
            "1",
            "0",
            "-1",
            "C"
        ));
        bank.addQuestion(new Question(
            "Which concept hides internal data using private fields?",
            "Inheritance",
            "Polymorphism",
            "Encapsulation",
            "Abstraction",
            "C"
        ));
        bank.addQuestion(new Question(
            "What does SQL stand for?",
            "Structured Query Language",
            "Simple Query Logic",
            "Standard Question List",
            "System Query Language",
            "A"
        ));
        bank.addQuestion(new Question(
            "Which loop runs at least once even if condition is false?",
            "for loop",
            "while loop",
            "do-while loop",
            "foreach loop",
            "C"
        ));
        bank.addQuestion(new Question(
            "What is the output of 5 % 2 in Java?",
            "2",
            "1",
            "0",
            "2.5",
            "B"
        ));
        bank.addQuestion(new Question(
            "Which collection allows duplicate elements in Java?",
            "HashSet",
            "TreeSet",
            "ArrayList",
            "LinkedHashSet",
            "C"
        ));
    }


    public static void main(String[] args) {
        loadDefaultQuestions();

        System.out.println("Online Quiz & Assessment System");
        System.out.println("Java Mini Project");

        while (true) {
            System.out.println("\n Main Menu ");
            System.out.println("    1. Take Quiz");
            System.out.println("    2. Admin Panel");
            System.out.println("    3. Exit");
            System.out.print("  Choice: ");
            String choice = sc.nextLine().trim();

            if (choice.equals("1"))      startQuiz();
            else if (choice.equals("2")) adminMenu();
            else if (choice.equals("3")) {
                System.out.println("\n  Goodbye! Keep learning. \n");
                break;
            } else {
                System.out.println("  Please pick 1, 2 or 3.");
            }
        }

        sc.close();
    }
}