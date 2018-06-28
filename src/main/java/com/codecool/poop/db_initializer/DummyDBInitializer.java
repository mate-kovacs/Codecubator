package com.codecool.poop.db_initializer;

import com.codecool.poop.model.Rooms;
import com.codecool.poop.model.assignments.coding.CodingQuestion;
import com.codecool.poop.model.assignments.quiz.QuizAnswer;
import com.codecool.poop.service.UserService;
import com.codecool.poop.service.coding_services.CodingAnswerService;
import com.codecool.poop.service.coding_services.CodingAssignmentService;
import com.codecool.poop.service.coding_services.CodingQuestionService;
import com.codecool.poop.service.quiz_services.QuizAnswerService;
import com.codecool.poop.service.quiz_services.QuizAssignmentService;
import com.codecool.poop.service.quiz_services.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import com.codecool.poop.model.Skills;
import com.codecool.poop.model.User;
import com.codecool.poop.model.assignments.coding.CodingAnswer;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import com.codecool.poop.model.assignments.quiz.QuizQuestion;
import org.mindrot.jbcrypt.BCrypt;
//
import java.util.*;

@Component
public class DummyDBInitializer {

    @Autowired
    private UserService userService;
    @Autowired
    private CodingAssignmentService codingAssignmentService;
    @Autowired
    private CodingQuestionService codingQuestionService;
    @Autowired
    private CodingAnswerService codingAnswerService;
    @Autowired
    private QuizAssignmentService quizAssignmentService;
    @Autowired
    private QuizQuestionService quizQuestionService;
    @Autowired
    private QuizAnswerService quizAnswerService;

    @PostConstruct
    public void init() {
        createUsers();
        createQuizAssignment();
        createCodingAssignments();
    }

    private void createUsers() {

        String name1 = "Aladár";
        String email1 = "aladar@poop.com";
        String password1 = "aaaaaaaa";
        String hashPW1 = BCrypt.hashpw(password1, BCrypt.gensalt(12));
        User user1 = new User(name1, hashPW1, email1);

        String name2 = "Béla";
        String email2 = "bela@poop.com";
        String password2 = "bbbbbbbb";
        String hashPW2 = BCrypt.hashpw(password2, BCrypt.gensalt(12));
        User user2 = new User(name2, hashPW2, email2);

        String name3 = "Cili";
        String email3 = "cili@poop.com";
        String password3 = "cccccccc";
        String hashPW3 = BCrypt.hashpw(password3, BCrypt.gensalt(12));
        User user3 = new User(name3, hashPW3, email3);

        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
    }

    private void createCodingAssignments() {

        CodingQuestion question1 = new CodingQuestion("SELECT * $ users $ id=1;");
        CodingAnswer answer11 = new CodingAnswer("FROM", question1);
        CodingAnswer answer12 = new CodingAnswer("WHERE", question1);

        CodingQuestion question2 = new CodingQuestion("INSERT $ products (product_name, product_description) $ ('USB charger', 'Provides much needed electricity to your beloved gadget.'$");
        CodingAnswer answer21 = new CodingAnswer("INTO", question2);
        CodingAnswer answer22 = new CodingAnswer("VALUES", question2);
        CodingAnswer answer23 = new CodingAnswer(");", question2);

        CodingQuestion question3 = new CodingQuestion("DELETE $ friends WHERE name $ 'Bob%';");
        CodingAnswer answer31 = new CodingAnswer("FROM", question3);
        CodingAnswer answer32 = new CodingAnswer("LIKE", question3);

        CodingQuestion question4 = new CodingQuestion("UPDATE addresses $ city='Budapest' WHERE zip LIKE '1$");
        CodingAnswer answer41 = new CodingAnswer("SET", question4);
        CodingAnswer answer42 = new CodingAnswer("%';", question4);

        CodingQuestion question5 = new CodingQuestion("SELECT name, movie_title FROM actors INNER $ ON $.movie=movies.protagonist WHERE release_date=1392;");
        CodingAnswer answer51 = new CodingAnswer("JOIN", question5);
        CodingAnswer answer52 = new CodingAnswer("actors", question5);

        CodingQuestion question6 = new CodingQuestion("INSERT INTO users (name, email, password) VALUES ('Robert $ DROP TABLE users;");
        CodingAnswer answer61 = new CodingAnswer("','','');", question6);

        List<CodingQuestion> questions1 = new ArrayList<>();
        questions1.add(question1);
        questions1.add(question2);
        questions1.add(question3);
        questions1.add(question4);
        questions1.add(question5);
        questions1.add(question6);

        Map<Skills, Integer> rewardMap1 = new HashMap<>();
        rewardMap1.put(Skills.DATA_STRUCTURES, 25);
        CodingAssignment codingAssignment1 = new CodingAssignment(
                "SQL basics",
                "A series of code completion assignments that cover most of the basic SQL queries and require a little extra knowledge.",
                rewardMap1,
                4,
                questions1,
                Rooms.GREEN_ROOM
        );
        codingAssignmentService.addCodingAssignment(codingAssignment1);


        CodingQuestion question7 = new CodingQuestion("List$ stringList = $ ArrayList<>();");
        CodingAnswer answer71 = new CodingAnswer("<String>", question7);
        CodingAnswer answer72 = new CodingAnswer("new", question7);

        CodingQuestion question8 = new CodingQuestion("public $ HelloWorld {\n  public" +
                " $(String asrgs[]) {\n    $.println(\"Hello World!\");\n  }\n}");
        CodingAnswer answer81 = new CodingAnswer("class", question8);
        CodingAnswer answer82 = new CodingAnswer("static void main", question8);
        CodingAnswer answer83 = new CodingAnswer("System.out", question8);

        CodingQuestion question9 = new CodingQuestion("public $ factorial(int n) {\n  " +
                "int result = 1;\n  for ($; i <= n; i++) {\n    " +
                "result *= i;\n  }\n  return result;");
        CodingAnswer answer91 = new CodingAnswer("int", question9);
        CodingAnswer answer92 = new CodingAnswer("int i = 1", question9);

        List<CodingQuestion> questions2 = new ArrayList<>();
        questions2.add(question7);
        questions2.add(question8);
        questions2.add(question9);

        Map<Skills, Integer> rewardMap2 = new HashMap<>();
        rewardMap2.put(Skills.JAVA_BASIC, 12);
        rewardMap2.put(Skills.OBJECT_ORIENTED_PROGRAMMING, 4);
        CodingAssignment codingAssignment2 = new CodingAssignment(
                "Java basics",
                "A series of very simple code completion tasks. Fill in all the blanks in the Java code correctly.",
                rewardMap2,
                2,
                questions2,
                Rooms.BLUE_ROOM
        );
        codingAssignmentService.addCodingAssignment(codingAssignment2);


    }

    private void createQuizAssignment() {
        QuizQuestion question1 = new QuizQuestion("What are the principles of Object Oriented Programming?");
        QuizAnswer answer11 = new QuizAnswer("Permutation", false, question1);
        QuizAnswer answer12 = new QuizAnswer("Polymophism", true, question1);
        QuizAnswer answer13 = new QuizAnswer("Inheritance", true, question1);
        QuizAnswer answer14 = new QuizAnswer("Implementation", false, question1);
        QuizAnswer answer15 = new QuizAnswer("Design Pattern", false, question1);
        QuizAnswer answer16 = new QuizAnswer("Encapsulation", true, question1);
        QuizAnswer answer17 = new QuizAnswer("Abstraction", true, question1);
        QuizAnswer answer18 = new QuizAnswer("Destruction", false, question1);

        QuizQuestion question2 = new QuizQuestion("Which of the following are basic types in Java?");
        QuizAnswer answer21 = new QuizAnswer("int", true, question2);
        QuizAnswer answer22 = new QuizAnswer("Double", false, question2);
        QuizAnswer answer23 = new QuizAnswer("boolean", true, question2);
        QuizAnswer answer24 = new QuizAnswer("Object", false, question2);
        QuizAnswer answer25 = new QuizAnswer("String", false, question2);
        QuizAnswer answer26 = new QuizAnswer("Array", false, question2);
        QuizAnswer answer27 = new QuizAnswer("char", true, question2);
        QuizAnswer answer28 = new QuizAnswer("Boolean", false, question2);

        QuizQuestion question3 = new QuizQuestion("Which of the following are implementations of Collection?");
        QuizAnswer answer31 = new QuizAnswer("ArrayList", true, question3);
        QuizAnswer answer32 = new QuizAnswer("LinkedList", true, question3);
        QuizAnswer answer33 = new QuizAnswer("Array", false, question3);
        QuizAnswer answer34 = new QuizAnswer("TreeSet", true, question3);
        QuizAnswer answer35 = new QuizAnswer("InputStream", false, question3);
        QuizAnswer answer36 = new QuizAnswer("Instant", false, question3);
        QuizAnswer answer37 = new QuizAnswer("Vector", true, question3);

        QuizQuestion question4 = new QuizQuestion("Which of the following are Checked Exceptions?");
        QuizAnswer answer41 = new QuizAnswer("IllegalArgumentException", false, question4);
        QuizAnswer answer42 = new QuizAnswer("NullPointerException", false, question4);
        QuizAnswer answer43 = new QuizAnswer("SQLException", true, question4);
        QuizAnswer answer44 = new QuizAnswer("IndexOutOfBoundsException", false, question4);
        QuizAnswer answer45 = new QuizAnswer("IOException", true, question4);
        QuizAnswer answer46 = new QuizAnswer("IllegalAccessException", true, question4);
        QuizAnswer answer47 = new QuizAnswer("UnsupportedOperationException", false, question4);
        QuizAnswer answer48 = new QuizAnswer("NegativeArraySizeException", false, question4);
        QuizAnswer answer49 = new QuizAnswer("FileNotFoundException", true, question4);

        QuizQuestion question5 = new QuizQuestion("What does the first argument of invoke(Object obj, Object args...) represent inside the ivoked method?");
        QuizAnswer answer51 = new QuizAnswer("null", false, question5);
        QuizAnswer answer52 = new QuizAnswer("this", true, question5);
        QuizAnswer answer53 = new QuizAnswer("super", false, question5);
        QuizAnswer answer54 = new QuizAnswer("class", false, question5);
        QuizAnswer answer55 = new QuizAnswer("method", false, question5);

        List<QuizQuestion> questions1 = new ArrayList<>();
        questions1.add(question1);
        questions1.add(question2);
        questions1.add(question3);
        questions1.add(question4);
        questions1.add(question5);

        Map<Skills, Integer> rewardMap1 = new HashMap<>();
        rewardMap1.put(Skills.JAVA_BASIC, 25);
        rewardMap1.put(Skills.OBJECT_ORIENTED_PROGRAMMING, 10);
        QuizAssignment quizAssignment1 = new QuizAssignment(
                "Java OOP intermediate",
                "A series of multiple choice questions regarding deeper knowledge of the Java language and Object Oriented Programming.",
                rewardMap1,
                4,
                questions1,
                Rooms.RED_ROOM
        );
        quizAssignmentService.addQuizAssignment(quizAssignment1);

        QuizQuestion question6 = new QuizQuestion("What is the O complexity of inserting an element into an ArrayList?");
        QuizAnswer answer61 = new QuizAnswer("O(1) ", false, question6);
        QuizAnswer answer62 = new QuizAnswer("O(n) ", true, question6);
        QuizAnswer answer63 = new QuizAnswer("O(n^2) ", false, question6);

        QuizQuestion question7 = new QuizQuestion("Which of the following algorythms work by switching adjacentelements in a list?");
        QuizAnswer answer71 = new QuizAnswer("Bubble sort ", true, question7);
        QuizAnswer answer72 = new QuizAnswer("Selection sort ", false, question7);
        QuizAnswer answer73 = new QuizAnswer("Bogo sort ", false, question7);

        QuizQuestion question8 = new QuizQuestion("To which of the following groups does 'top-margin' belong?");
        QuizAnswer answer81 = new QuizAnswer("HTML tag ", false, question8);
        QuizAnswer answer82 = new QuizAnswer("CSS selector ", false, question8);
        QuizAnswer answer83 = new QuizAnswer("CSS property ", true, question8);

        QuizQuestion question9 = new QuizQuestion("To which of the following groups does '#class' belong?");
        QuizAnswer answer91 = new QuizAnswer("HTML tag ", false, question9);
        QuizAnswer answer92 = new QuizAnswer("CSS selector ", true, question9);
        QuizAnswer answer93 = new QuizAnswer("CSS property ", false, question9);

        QuizQuestion question10 = new QuizQuestion("Select the TRUE statements!");
        QuizAnswer answer101 = new QuizAnswer("HTML stands for Hypertext Markup Language ", true, question10);
        QuizAnswer answer102 = new QuizAnswer("CSS stands for Cascading Style Sheet ", true, question10);
        QuizAnswer answer103 = new QuizAnswer("JSON stands for Java Script Object Notation ", true, question10);

        QuizQuestion question11 = new QuizQuestion("Select the TRUE statements!");
        QuizAnswer answer111 = new QuizAnswer("GNU is not Unix ", true, question11);
        QuizAnswer answer112 = new QuizAnswer("GNU is Unix ", false, question11);

        List<QuizQuestion> questions2 = new ArrayList<>();
        questions2.add(question6);
        questions2.add(question7);
        questions2.add(question8);
        questions2.add(question9);
        questions2.add(question10);
        questions2.add(question11);

        Map<Skills, Integer> rewardMap2 = new HashMap<>();
        rewardMap2.put(Skills.ALGORITHMS, 12);
        rewardMap2.put(Skills.CSS_BASIC, 5);
        rewardMap2.put(Skills.HTML_BASIC, 5);
        QuizAssignment quizAssignment2 = new QuizAssignment(
                "A little bit of everything",
                "A series of very short questions regarding algorythms, web design, and touching on Operation Systems.",
                rewardMap2,
                3,
                questions2,
                Rooms.GREEN_ROOM
        );
        quizAssignmentService.addQuizAssignment(quizAssignment2);
    }
}
