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

        CodingQuestion question10 = new CodingQuestion("$ my_function(param1, param2)$");
        CodingAnswer answer101 = new CodingAnswer("def", question10);
        CodingAnswer answer102 = new CodingAnswer(":", question10);

        CodingQuestion question11 = new CodingQuestion("new_list = [element $ element $ old_list $ element < 12]");
        CodingAnswer answer111 = new CodingAnswer("for", question11);
        CodingAnswer answer112 = new CodingAnswer("in", question11);
        CodingAnswer answer113 = new CodingAnswer("if", question11);

        CodingQuestion question12 = new CodingQuestion("counter $ counter+1 $ element==sample $ counter");
        CodingAnswer answer121 = new CodingAnswer("=", question12);
        CodingAnswer answer122 = new CodingAnswer("if", question12);
        CodingAnswer answer123 = new CodingAnswer("else", question12);

        List<CodingQuestion> questions3 = new ArrayList<>();
        questions3.add(question10);
        questions3.add(question11);
        questions3.add(question12);

        Map<Skills, Integer> rewardMap3 = new HashMap<>();
        rewardMap3.put(Skills.PYTHON_BASIC, 15);
        rewardMap3.put(Skills.FUNCTIONAL_PROGRAMMING, 2);
        CodingAssignment codingAssignment3 = new CodingAssignment(
                "Python basics",
                "A series of simple code completion tasks in the well-known and beloved Python language. Fill in all the blanks in the Java code correctly.",
                rewardMap3,
                2,
                questions3,
                Rooms.WHITE_ROOM
        );
        codingAssignmentService.addCodingAssignment(codingAssignment3);

        CodingQuestion question13 = new CodingQuestion("$ commit $ 'lorem ipsum dolor sit amet'");
        CodingAnswer answer131 = new CodingAnswer("git", question13);
        CodingAnswer answer132 = new CodingAnswer("-m", question13);

        CodingQuestion question14 = new CodingQuestion("git reset $hard $^");
        CodingAnswer answer141 = new CodingAnswer("--", question14);
        CodingAnswer answer142 = new CodingAnswer("HEAD", question14);

        CodingQuestion question15 = new CodingQuestion("git $ -b new_branch");
        CodingAnswer answer151 = new CodingAnswer("checkout", question15);

        List<CodingQuestion> questions4 = new ArrayList<>();
        questions4.add(question13);
        questions4.add(question14);
        questions4.add(question15);

        Map<Skills, Integer> rewardMap4 = new HashMap<>();
        rewardMap4.put(Skills.DATA_STRUCTURES, 10);
        rewardMap4.put(Skills.PATTERNS, 2);
        CodingAssignment codingAssignment4 = new CodingAssignment(
                "Git basics",
                "A series of simple code completion tasks in which you can test how well do you remember git commands from before the times of smart IDEs.",
                rewardMap4,
                2,
                questions4,
                Rooms.GREEN_ROOM
        );
        codingAssignmentService.addCodingAssignment(codingAssignment4);
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

        QuizQuestion question12 = new QuizQuestion("What is the highest number in the following list: range(12)?");
        QuizAnswer answer121 = new QuizAnswer("11 ", false, question12);
        QuizAnswer answer122 = new QuizAnswer("12 ", true, question12);
        QuizAnswer answer123 = new QuizAnswer("13 ", false, question12);

        QuizQuestion question13 = new QuizQuestion("How do you correctly include Math.random?");
        QuizAnswer answer131 = new QuizAnswer("import Math.random ", false, question13);
        QuizAnswer answer132 = new QuizAnswer("include Math.random ", false, question13);
        QuizAnswer answer133 = new QuizAnswer("from Math import random ", true, question13);
        QuizAnswer answer134 = new QuizAnswer("from Math include random ", false, question13);

        QuizQuestion question14 = new QuizQuestion("How do you indicate the evaluation of a condition on a flowchart?");
        QuizAnswer answer141 = new QuizAnswer("Triangle ", false, question12);
        QuizAnswer answer142 = new QuizAnswer("Cube ", false, question12);
        QuizAnswer answer143 = new QuizAnswer("Rectangle ", false, question12);
        QuizAnswer answer144 = new QuizAnswer("Circle ", false, question12);
        QuizAnswer answer145 = new QuizAnswer("Diamond ", true, question12);

        QuizQuestion question15 = new QuizQuestion("Select the members of a pair programming session!");
        QuizAnswer answer151 = new QuizAnswer("Pilot ", false, question15);
        QuizAnswer answer152 = new QuizAnswer("Co-pilot ", false, question15);
        QuizAnswer answer153 = new QuizAnswer("Driver ", true, question15);
        QuizAnswer answer154 = new QuizAnswer("Navigator ", true, question15);
        QuizAnswer answer155 = new QuizAnswer("User ", false, question15);
        QuizAnswer answer156 = new QuizAnswer("Administrator ", false, question15);
        QuizAnswer answer157 = new QuizAnswer("Senior ", false, question15);
        QuizAnswer answer158 = new QuizAnswer("Junior ", false, question15);

        List<QuizQuestion> questions3 = new ArrayList<>();
        questions3.add(question12);
        questions3.add(question13);
        questions3.add(question14);
        questions3.add(question15);

        Map<Skills, Integer> rewardMap3 = new HashMap<>();
        rewardMap3.put(Skills.PYTHON_BASIC, 10);
        rewardMap3.put(Skills.ALGORITHMS, 5);
        QuizAssignment quizAssignment3 = new QuizAssignment(
                "Basics with Python",
                "A series of multiple choice questions about basic concepts in Python, and some other useful stuff for beginners.",
                rewardMap3,
                3,
                questions3,
                Rooms.WHITE_ROOM
        );
        quizAssignmentService.addQuizAssignment(quizAssignment3);

        QuizQuestion question16 = new QuizQuestion("What do you call a graph with nodes that can not be connected through edges?");
        QuizAnswer answer161 = new QuizAnswer("Disjoint graph ", true, question16);
        QuizAnswer answer162 = new QuizAnswer("Simple graph ", false, question16);
        QuizAnswer answer163 = new QuizAnswer("Joint graph ", false, question16);
        QuizAnswer answer164 = new QuizAnswer("Tree ", false, question16);
        QuizAnswer answer165 = new QuizAnswer("Connected graph ", false, question16);
        QuizAnswer answer166 = new QuizAnswer("Discnnected graph ", false, question16);

        QuizQuestion question17 = new QuizQuestion("What do you call a graph with no loops?");
        QuizAnswer answer171 = new QuizAnswer("Circleless graph ", false, question17);
        QuizAnswer answer172 = new QuizAnswer("Simple graph ", false, question17);
        QuizAnswer answer173 = new QuizAnswer("Dislooped graph ", false, question17);
        QuizAnswer answer174 = new QuizAnswer("Tree ", false, question17);
        QuizAnswer answer175 = new QuizAnswer("Connected graph ", false, question17);
        QuizAnswer answer176 = new QuizAnswer("Acyclic graph ", true, question17);

        QuizQuestion question18 = new QuizQuestion("What do you call an acyclic and connected graph?");
        QuizAnswer answer181 = new QuizAnswer("Map ", false, question18);
        QuizAnswer answer182 = new QuizAnswer("Simple graph ", false, question18);
        QuizAnswer answer183 = new QuizAnswer("Set ", false, question18);
        QuizAnswer answer184 = new QuizAnswer("Tree ", true, question18);
        QuizAnswer answer185 = new QuizAnswer("Cyclic graph ", false, question18);
        QuizAnswer answer186 = new QuizAnswer("Complete graph ", false, question18);

        QuizQuestion question19 = new QuizQuestion("What do you call a path in a graph that visits each vertex exactly once?");
        QuizAnswer answer191 = new QuizAnswer("Complete path ", false, question19);
        QuizAnswer answer192 = new QuizAnswer("Liskov path ", false, question19);
        QuizAnswer answer193 = new QuizAnswer("Hamilton path ", true, question19);

        QuizQuestion question20 = new QuizQuestion("What is the big O complexity of finding an element on a balanced binary search tree?");
        QuizAnswer answer201 = new QuizAnswer("O(1) ", false, question20);
        QuizAnswer answer202 = new QuizAnswer("O(log2(N)) ", true, question20);
        QuizAnswer answer203 = new QuizAnswer("O(N) ", false, question20);
        QuizAnswer answer204 = new QuizAnswer("O(2N) ", false, question20);
        QuizAnswer answer205 = new QuizAnswer("O(N^2) ", false, question20);

        List<QuizQuestion> questions4 = new ArrayList<>();
        questions4.add(question16);
        questions4.add(question17);
        questions4.add(question18);
        questions4.add(question19);
        questions4.add(question20);

        Map<Skills, Integer> rewardMap4 = new HashMap<>();
        rewardMap4.put(Skills.DATA_STRUCTURES, 20);
        QuizAssignment quizAssignment4 = new QuizAssignment(
                "Data structures: Graphs",
                "A series of multiple choice questions about graphs.",
                rewardMap4,
                5,
                questions4,
                Rooms.BLUE_ROOM
        );
        quizAssignmentService.addQuizAssignment(quizAssignment4);

        QuizQuestion question21 = new QuizQuestion("Magic?");
        QuizAnswer answer211 = new QuizAnswer("Magic ", true, question21);

        List<QuizQuestion> questions5 = new ArrayList<>();
        questions5.add(question21);

        Map<Skills, Integer> rewardMap5 = new HashMap<>();
        rewardMap5.put(Skills.PATTERNS, 2);
        rewardMap5.put(Skills.OBJECT_ORIENTED_PROGRAMMING, 2);
        QuizAssignment quizAssignment5 = new QuizAssignment(
                "Spring",
                "The most important concepts about the Spring framework.",
                rewardMap5,
                1,
                questions5,
                Rooms.RED_ROOM
        );
        quizAssignmentService.addQuizAssignment(quizAssignment5);

    }
}
