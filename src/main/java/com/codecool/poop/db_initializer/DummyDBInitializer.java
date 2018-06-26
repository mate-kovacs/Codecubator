//package com.codecool.poop.db_initializer;
//
//import com.codecool.poop.dao.CodingQuestManager;
//import com.codecool.poop.dao.MasteryQuestManager;
//import com.codecool.poop.dao.UserManager;
//import com.codecool.poop.model.Skills;
//import com.codecool.poop.model.User;
//import com.codecool.poop.model.assignments.coding.CodingAnswer;
//import com.codecool.poop.model.assignments.coding.CodingAssignment;
//import com.codecool.poop.model.assignments.coding.CodingQuestion;
//import com.codecool.poop.model.assignments.mastery.MasteryAssignment;
//import com.codecool.poop.model.assignments.quiz.QuizAnswer;
//import com.codecool.poop.model.assignments.quiz.QuizAssignment;
//import com.codecool.poop.model.assignments.quiz.QuizQuestion;
//import org.mindrot.jbcrypt.BCrypt;
//
//import java.util.*;
//
//public class DummyDBInitializer {
//
//    private UserManager userManager;
//    private QuizQuestManager quizManager;
//    private CodingQuestManager codingManager;
//    private MasteryQuestManager masteryManager;
//
//    public DummyDBInitializer(UserManager userManager,
//                              QuizQuestManager quizManager,
//                              CodingQuestManager codingManager,
//                              MasteryQuestManager masteryManager) {
//        this.userManager = userManager;
//        this.quizManager = quizManager;
//        this.codingManager = codingManager;
//        this.masteryManager = masteryManager;
//    }
//
//    public void initializeDummyDB() {
//
//        createUsers();
//        createCodingAssignments();
//        createQuizAssignment();
//        createMasteryAssignments();
//    }
//
//    private void createUsers() {
//
//        String name1 = "Aladár";
//        String email1 = "aladar@poop.com";
//        String password1 = "aaaaaaaa";
//        String hashPW1 = BCrypt.hashpw(password1, BCrypt.gensalt(12));
//        User user1 = new User(name1, hashPW1, email1);
//
//        String name2 = "Béla";
//        String email2 = "bela@poop.com";
//        String password2 = "bbbbbbbb";
//        String hashPW2 = BCrypt.hashpw(password2, BCrypt.gensalt(12));
//        User user2 = new User(name2, hashPW2, email2);
//
//        String name3 = "Cili";
//        String email3 = "cili@poop.com";
//        String password3 = "cccccccc";
//        String hashPW3 = BCrypt.hashpw(password3, BCrypt.gensalt(12));
//        User user3 = new User(name3, hashPW3, email3);
//
//        userManager.registerUser(user1);
//        userManager.registerUser(user2);
//        userManager.registerUser(user3);
//    }
//
//    private void createCodingAssignments() {
//
//        CodingQuestion question1 = new CodingQuestion("SELECT * $ users $ id=1;");
//        codingManager.addCodingQuestionToDB(question1);
//        CodingAnswer answer11 = new CodingAnswer("FROM", question1);
//        codingManager.addCodingAnswerToDB(answer11);
//        CodingAnswer answer12 = new CodingAnswer("WHERE", question1);
//        codingManager.addCodingAnswerToDB(answer12);
//
//        CodingQuestion question2 = new CodingQuestion("INSERT $ products (product_name, product_description) $ ('USB charger', 'Provides much needed electricity to your beloved gadget.'$");
//        codingManager.addCodingQuestionToDB(question2);
//        CodingAnswer answer21 = new CodingAnswer("INTO", question2);
//        codingManager.addCodingAnswerToDB(answer21);
//        CodingAnswer answer22 = new CodingAnswer("VALUES", question2);
//        codingManager.addCodingAnswerToDB(answer22);
//        CodingAnswer answer23 = new CodingAnswer(");", question2);
//        codingManager.addCodingAnswerToDB(answer23);
//
//        CodingQuestion question3 = new CodingQuestion("DELETE $ friends WHERE name $ 'Bob%';");
//        codingManager.addCodingQuestionToDB(question3);
//        CodingAnswer answer31 = new CodingAnswer("FROM", question3);
//        codingManager.addCodingAnswerToDB(answer31);
//        CodingAnswer answer32 = new CodingAnswer("LIKE", question3);
//        codingManager.addCodingAnswerToDB(answer32);
//
//        CodingQuestion question4 = new CodingQuestion("UPDATE addresses $ city='Budapest' WHERE zip LIKE '1$");
//        codingManager.addCodingQuestionToDB(question4);
//        CodingAnswer answer41 = new CodingAnswer("SET", question4);
//        codingManager.addCodingAnswerToDB(answer41);
//        CodingAnswer answer42 = new CodingAnswer("%';", question4);
//        codingManager.addCodingAnswerToDB(answer42);
//
//        CodingQuestion question5 = new CodingQuestion("SELECT name, movie_title FROM actors INNER $ ON $.movie=movies.protagonist WHERE release_date=1392;");
//        codingManager.addCodingQuestionToDB(question5);
//        CodingAnswer answer51 = new CodingAnswer("JOIN", question5);
//        codingManager.addCodingAnswerToDB(answer51);
//        CodingAnswer answer52 = new CodingAnswer("actors", question5);
//        codingManager.addCodingAnswerToDB(answer52);
//
//        CodingQuestion question6 = new CodingQuestion("INSERT INTO users (name, email, password) VALUES ('Robert $ DROP TABLE users;");
//        codingManager.addCodingQuestionToDB(question6);
//        CodingAnswer answer61 = new CodingAnswer("','','');", question6);
//        codingManager.addCodingAnswerToDB(answer61);
//
//        List<CodingQuestion> questions1 = new ArrayList<>();
//        questions1.add(question1);
//        questions1.add(question2);
//        questions1.add(question3);
//        questions1.add(question4);
//        questions1.add(question5);
//        questions1.add(question6);
//
//        Map<Skills, Integer> rewardMap1 = new HashMap<>();
//        rewardMap1.put(Skills.DATA_STRUCTURES, 25);
//        CodingAssignment codingAssignment1 = new CodingAssignment(
//                "SQL basics",
//                "A series of code completion assignments that cover most of the basic SQL queries and require a little extra knowledge.",
//                rewardMap1,
//                4,
//                questions1
//        );
//        codingManager.addCodingAssignmentToDB(codingAssignment1);
//
//
//        CodingQuestion question7 = new CodingQuestion("List$ stringList = $ ArrayList<>();");
//        codingManager.addCodingQuestionToDB(question7);
//        CodingAnswer answer71 = new CodingAnswer("<String>", question7);
//        codingManager.addCodingAnswerToDB(answer71);
//        CodingAnswer answer72 = new CodingAnswer("new", question7);
//        codingManager.addCodingAnswerToDB(answer72);
//
//        CodingQuestion question8 = new CodingQuestion("public $ HelloWorld {\n  public" +
//                " $(String asrgs[]) {\n    $.println(\"Hello World!\");\n  }\n}");
//        codingManager.addCodingQuestionToDB(question8);
//        CodingAnswer answer81 = new CodingAnswer("class", question8);
//        codingManager.addCodingAnswerToDB(answer81);
//        CodingAnswer answer82 = new CodingAnswer("static void main", question8);
//        codingManager.addCodingAnswerToDB(answer82);
//        CodingAnswer answer83 = new CodingAnswer("System.out", question8);
//        codingManager.addCodingAnswerToDB(answer83);
//
//        CodingQuestion question9 = new CodingQuestion("public $ factorial(int n) {\n  " +
//                "int result = 1;\n  for ($; i <= n; i++) {\n    " +
//                "result *= i;\n  }\n  return result;");
//        codingManager.addCodingQuestionToDB(question9);
//        CodingAnswer answer91 = new CodingAnswer("int", question9);
//        codingManager.addCodingAnswerToDB(answer91);
//        CodingAnswer answer92 = new CodingAnswer("int i = 1", question9);
//        codingManager.addCodingAnswerToDB(answer92);
//
//        List<CodingQuestion> questions2 = new ArrayList<>();
//        questions2.add(question7);
//        questions2.add(question8);
//        questions2.add(question9);
//
//        Map<Skills, Integer> rewardMap2 = new HashMap<>();
//        rewardMap2.put(Skills.JAVA_BASIC, 12);
//        rewardMap2.put(Skills.OBJECT_ORIENTED_PROGRAMMING, 4);
//        CodingAssignment codingAssignment2 = new CodingAssignment(
//                "Java basics",
//                "A series of very simple code completion tasks. Fill in all the blanks in the Java code correctly.",
//                rewardMap2,
//                2,
//                questions2
//        );
//        codingManager.addCodingAssignmentToDB(codingAssignment2);
//
//
//    }
//
//    private void createQuizAssignment() {
//        QuizQuestion question1 = new QuizQuestion("What are the principles of Object Oriented Programming?");
//        quizManager.addQuizQuestionToDB(question1);
//        QuizAnswer answer11 = new QuizAnswer("Pemutation", false, question1);
//        quizManager.addQuizAnswerToDB(answer11);
//        QuizAnswer answer12 = new QuizAnswer("Polymophism", true, question1);
//        quizManager.addQuizAnswerToDB(answer12);
//        QuizAnswer answer13 = new QuizAnswer("Inheritance", true, question1);
//        quizManager.addQuizAnswerToDB(answer13);
//        QuizAnswer answer14 = new QuizAnswer("Implementation", false, question1);
//        quizManager.addQuizAnswerToDB(answer14);
//        QuizAnswer answer15 = new QuizAnswer("Design Pattern", false, question1);
//        quizManager.addQuizAnswerToDB(answer15);
//        QuizAnswer answer16 = new QuizAnswer("Encapsulation", true, question1);
//        quizManager.addQuizAnswerToDB(answer16);
//        QuizAnswer answer17 = new QuizAnswer("Abstraction", true, question1);
//        quizManager.addQuizAnswerToDB(answer17);
//        QuizAnswer answer18 = new QuizAnswer("Destruction", false, question1);
//        quizManager.addQuizAnswerToDB(answer18);
//
//        QuizQuestion question2 = new QuizQuestion("Which of the following are basic types in Java?");
//        quizManager.addQuizQuestionToDB(question2);
//        QuizAnswer answer21 = new QuizAnswer("int", true, question2);
//        quizManager.addQuizAnswerToDB(answer21);
//        QuizAnswer answer22 = new QuizAnswer("Double", false, question2);
//        quizManager.addQuizAnswerToDB(answer22);
//        QuizAnswer answer23 = new QuizAnswer("boolean", true, question2);
//        quizManager.addQuizAnswerToDB(answer23);
//        QuizAnswer answer24 = new QuizAnswer("Object", false, question2);
//        quizManager.addQuizAnswerToDB(answer24);
//        QuizAnswer answer25 = new QuizAnswer("String", false, question2);
//        quizManager.addQuizAnswerToDB(answer25);
//        QuizAnswer answer26 = new QuizAnswer("Array", false, question2);
//        quizManager.addQuizAnswerToDB(answer26);
//        QuizAnswer answer27 = new QuizAnswer("char", true, question2);
//        quizManager.addQuizAnswerToDB(answer27);
//        QuizAnswer answer28 = new QuizAnswer("Boolean", false, question2);
//        quizManager.addQuizAnswerToDB(answer28);
//
//        QuizQuestion question3 = new QuizQuestion("Which of the following are implementations of Collection?");
//        quizManager.addQuizQuestionToDB(question3);
//        QuizAnswer answer31 = new QuizAnswer("ArrayList", true, question3);
//        quizManager.addQuizAnswerToDB(answer31);
//        QuizAnswer answer32 = new QuizAnswer("LinkedList", true, question3);
//        quizManager.addQuizAnswerToDB(answer32);
//        QuizAnswer answer33 = new QuizAnswer("Array", false, question3);
//        quizManager.addQuizAnswerToDB(answer33);
//        QuizAnswer answer34 = new QuizAnswer("TreeSet", true, question3);
//        quizManager.addQuizAnswerToDB(answer34);
//        QuizAnswer answer35 = new QuizAnswer("InputStream", false, question3);
//        quizManager.addQuizAnswerToDB(answer35);
//        QuizAnswer answer36 = new QuizAnswer("Instant", false, question3);
//        quizManager.addQuizAnswerToDB(answer36);
//        QuizAnswer answer37 = new QuizAnswer("Vector", true, question3);
//        quizManager.addQuizAnswerToDB(answer37);
//
//        QuizQuestion question4 = new QuizQuestion("Which of the following are Checked Exceptions?");
//        quizManager.addQuizQuestionToDB(question4);
//        QuizAnswer answer41 = new QuizAnswer("IllegalArgumentException", false, question4);
//        quizManager.addQuizAnswerToDB(answer41);
//        QuizAnswer answer42 = new QuizAnswer("NullPointerException", false, question4);
//        quizManager.addQuizAnswerToDB(answer42);
//        QuizAnswer answer43 = new QuizAnswer("SQLException", true, question4);
//        quizManager.addQuizAnswerToDB(answer43);
//        QuizAnswer answer44 = new QuizAnswer("IndexOutOfBoundsException", false, question4);
//        quizManager.addQuizAnswerToDB(answer44);
//        QuizAnswer answer45 = new QuizAnswer("IOException", true, question4);
//        quizManager.addQuizAnswerToDB(answer45);
//        QuizAnswer answer46 = new QuizAnswer("IllegalAccessException", true, question4);
//        quizManager.addQuizAnswerToDB(answer46);
//        QuizAnswer answer47 = new QuizAnswer("UnsupportedOperationException", false, question4);
//        quizManager.addQuizAnswerToDB(answer47);
//        QuizAnswer answer48 = new QuizAnswer("NegativeArraySizeException", false, question4);
//        quizManager.addQuizAnswerToDB(answer48);
//        QuizAnswer answer49 = new QuizAnswer("FileNotFoundException", true, question4);
//        quizManager.addQuizAnswerToDB(answer49);
//
//        QuizQuestion question5 = new QuizQuestion("What does the first argument of invoke(Pbject obj, Object args...) represent inside the ivoked method?");
//        quizManager.addQuizQuestionToDB(question5);
//        QuizAnswer answer51 = new QuizAnswer("null", false, question5);
//        quizManager.addQuizAnswerToDB(answer51);
//        QuizAnswer answer52 = new QuizAnswer("this", true, question5);
//        quizManager.addQuizAnswerToDB(answer52);
//        QuizAnswer answer53 = new QuizAnswer("super", false, question5);
//        quizManager.addQuizAnswerToDB(answer53);
//        QuizAnswer answer54 = new QuizAnswer("class", false, question5);
//        quizManager.addQuizAnswerToDB(answer54);
//        QuizAnswer answer55 = new QuizAnswer("method", false, question5);
//        quizManager.addQuizAnswerToDB(answer55);
//
//        List<QuizQuestion> questions1 = new ArrayList<>();
//        questions1.add(question1);
//        questions1.add(question2);
//        questions1.add(question3);
//        questions1.add(question4);
//        questions1.add(question5);
//
//        Map<Skills, Integer> rewardMap1 = new HashMap<>();
//        rewardMap1.put(Skills.JAVA_BASIC, 25);
//        rewardMap1.put(Skills.OBJECT_ORIENTED_PROGRAMMING, 10);
//        QuizAssignment quizAssignment1 = new QuizAssignment(
//                "Java OOP intermediate",
//                "A series of multiple choice questions regarding deeper knowledge of the Java language and Object Oriented Programming.",
//                rewardMap1,
//                4,
//                questions1
//        );
//        quizManager.addQuizAssignmentToDB(quizAssignment1);
//
//        QuizQuestion question6 = new QuizQuestion("What is the O complexity of inserting an element into an ArrayList?");
//        quizManager.addQuizQuestionToDB(question6);
//        QuizAnswer answer61 = new QuizAnswer("O(1) ", false, question6);
//        quizManager.addQuizAnswerToDB(answer61);
//        QuizAnswer answer62 = new QuizAnswer("O(n) ", true, question6);
//        quizManager.addQuizAnswerToDB(answer62);
//        QuizAnswer answer63 = new QuizAnswer("O(n^2) ", false, question6);
//        quizManager.addQuizAnswerToDB(answer63);
//
//        QuizQuestion question7 = new QuizQuestion("Which of the following algorythms work by switching adjacentelements in a list?");
//        quizManager.addQuizQuestionToDB(question7);
//        QuizAnswer answer71 = new QuizAnswer("Bubble sort ", true, question7);
//        quizManager.addQuizAnswerToDB(answer71);
//        QuizAnswer answer72 = new QuizAnswer("Selection sort ", false, question7);
//        quizManager.addQuizAnswerToDB(answer72);
//        QuizAnswer answer73 = new QuizAnswer("Bogo sort ", false, question7);
//        quizManager.addQuizAnswerToDB(answer73);
//
//        QuizQuestion question8 = new QuizQuestion("To which of the following groups does 'top-margin' belong?");
//        quizManager.addQuizQuestionToDB(question8);
//        QuizAnswer answer81 = new QuizAnswer("HTML tag ", false, question8);
//        quizManager.addQuizAnswerToDB(answer81);
//        QuizAnswer answer82 = new QuizAnswer("CSS selector ", false, question8);
//        quizManager.addQuizAnswerToDB(answer82);
//        QuizAnswer answer83 = new QuizAnswer("CSS property ", true, question8);
//        quizManager.addQuizAnswerToDB(answer83);
//
//        QuizQuestion question9 = new QuizQuestion("To which of the following groups does '#class' belong?");
//        quizManager.addQuizQuestionToDB(question9);
//        QuizAnswer answer91 = new QuizAnswer("HTML tag ", false, question9);
//        quizManager.addQuizAnswerToDB(answer91);
//        QuizAnswer answer92 = new QuizAnswer("CSS selector ", true, question9);
//        quizManager.addQuizAnswerToDB(answer92);
//        QuizAnswer answer93 = new QuizAnswer("CSS property ", false, question9);
//        quizManager.addQuizAnswerToDB(answer93);
//
//        QuizQuestion question10 = new QuizQuestion("Select the TRUE statements!");
//        quizManager.addQuizQuestionToDB(question10);
//        QuizAnswer answer101 = new QuizAnswer("HTML stands for Hypertext Markup Language ", true, question10);
//        quizManager.addQuizAnswerToDB(answer101);
//        QuizAnswer answer102 = new QuizAnswer("CSS stands for Cascading Style Sheet ", true, question10);
//        quizManager.addQuizAnswerToDB(answer102);
//        QuizAnswer answer103 = new QuizAnswer("JSON stands for Java Script Object Notation ", true, question10);
//        quizManager.addQuizAnswerToDB(answer103);
//
//        QuizQuestion question11 = new QuizQuestion("Select the TRUE statements!");
//        quizManager.addQuizQuestionToDB(question11);
//        QuizAnswer answer111 = new QuizAnswer("GNU is not Unix ", true, question11);
//        quizManager.addQuizAnswerToDB(answer111);
//        QuizAnswer answer112 = new QuizAnswer("GNU is Unix ", false, question11);
//        quizManager.addQuizAnswerToDB(answer112);
//
//        List<QuizQuestion> questions2 = new ArrayList<>();
//        questions2.add(question6);
//        questions2.add(question7);
//        questions2.add(question8);
//        questions2.add(question9);
//        questions2.add(question10);
//        questions2.add(question11);
//
//        Map<Skills, Integer> rewardMap2 = new HashMap<>();
//        rewardMap2.put(Skills.ALGORITHMS, 12);
//        rewardMap2.put(Skills.CSS_BASIC, 5);
//        rewardMap2.put(Skills.HTML_BASIC, 5);
//        QuizAssignment quizAssignment2 = new QuizAssignment(
//                "A little bit of everything",
//                "A series of very short questions regarding algorythms, web design, and touching on Operation Systems.",
//                rewardMap2,
//                3,
//                questions2
//        );
//        quizManager.addQuizAssignmentToDB(quizAssignment2);
//
//
//    }
//
//    private void createMasteryAssignments() {
//        Map<Skills, Integer> reward = new HashMap<>();
//        reward.put(Skills.ALGORITHMS, 10);
//        MasteryAssignment assignment = new MasteryAssignment("Name",
//                "Description",
//                reward,
//                5,
//                null,
//                null);
//        QuizQuestion question11 = new QuizQuestion("Quiz question");
//        QuizQuestion question12 = new QuizQuestion("Quit question");
//        CodingQuestion question21 = new CodingQuestion("Coding question");
//        CodingQuestion question22 = new CodingQuestion("Modding question");
//        List<QuizQuestion> quizQuestions = new ArrayList<>();
//        List<CodingQuestion> codingQuestions = new ArrayList<>();
//        quizQuestions.add(question11);
//        quizQuestions.add(question12);
//        codingQuestions.add(question21);
//        codingQuestions.add(question22);
//
//        for (CodingQuestion question: codingQuestions) {
//            codingManager.addCodingQuestionToDB(question);
//        }
//        for (QuizQuestion question: quizQuestions) {
//            quizManager.addQuizQuestionToDB(question);
//        }
//
//        masteryManager.addMasteryAssignmentToDB(assignment);
//    }
//}
