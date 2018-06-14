package com.codecool.poop.db_initializer;

import com.codecool.poop.dao.CodingQuestManager;
import com.codecool.poop.dao.MasteryQuestManager;
import com.codecool.poop.dao.QuizQuestManager;
import com.codecool.poop.dao.UserManager;
import com.codecool.poop.model.Skills;
import com.codecool.poop.model.User;
import com.codecool.poop.model.assignments.coding.CodingAnswer;
import com.codecool.poop.model.assignments.coding.CodingAssignment;
import com.codecool.poop.model.assignments.coding.CodingQuestion;
import com.codecool.poop.model.assignments.mastery.MasteryAssignment;
import com.codecool.poop.model.assignments.quiz.QuizAnswer;
import com.codecool.poop.model.assignments.quiz.QuizAssignment;
import com.codecool.poop.model.assignments.quiz.QuizQuestion;
import org.mindrot.jbcrypt.BCrypt;

import java.util.*;

public class DummyDBInitializer {

    private UserManager userManager;
    private QuizQuestManager quizManager;
    private CodingQuestManager codingManager;
    private MasteryQuestManager masteryManager;

    public DummyDBInitializer(UserManager userManager,
                              QuizQuestManager quizManager,
                              CodingQuestManager codingManager,
                              MasteryQuestManager masteryManager) {
        this.userManager = userManager;
        this.quizManager = quizManager;
        this.codingManager = codingManager;
        this.masteryManager = masteryManager;
    }

    public void initializeDummyDB() {

        createUsers();
        createCodingAssignments();
        createQuizAssignment();
        createMasteryAssignments();
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

        userManager.registerUser(user1);
        userManager.registerUser(user2);
        userManager.registerUser(user3);
    }

    private void createCodingAssignments() {
        Map<Skills, Integer> reward = new HashMap<>();
        reward.put(Skills.HTML_BASIC, 5);
        reward.put(Skills.CSS_BASIC, 8);
        List<CodingQuestion> questions = new ArrayList<>();
        CodingQuestion question11 = new CodingQuestion("To $ or not $ be this is $ question");
        CodingQuestion question12 = new CodingQuestion("May the $ be with $");
        List<CodingQuestion> questions2 = new ArrayList<>();
        CodingQuestion question21 = new CodingQuestion("How?");
        CodingQuestion question22 = new CodingQuestion("Who?");

        List<CodingAnswer> answers11 = new ArrayList<>();
        answers11.add(new CodingAnswer("be", question11));
        answers11.add(new CodingAnswer("to", question11));
        answers11.add(new CodingAnswer("the", question11));
        List<CodingAnswer> answers12 = new ArrayList<>();
        answers12.add(new CodingAnswer("force", question12));
        answers12.add(new CodingAnswer("you", question12));
        List<CodingAnswer> answers21 = new ArrayList<>();
        answers21.add(new CodingAnswer("Fourty", question21));
        answers21.add(new CodingAnswer("Two", question21));
        List<CodingAnswer> answers22 = new ArrayList<>();
        answers22.add(new CodingAnswer("Me", question22));

        questions.add(question11);
        questions.add(question12);
        questions2.add(question21);
        questions2.add(question22);

        CodingAssignment assignment = new CodingAssignment("Number one",
                "Description",
                reward,
                3,
                questions);
        for (CodingQuestion question : questions) {
            question.addAssignment(assignment);
        }
        CodingAssignment assignment2 = new CodingAssignment("Number two",
                "Other",
                reward,
                2,
                questions);
        for (CodingQuestion question : questions2) {
            question.addAssignment(assignment2);
        }

        codingManager.addCodingAssignmentToDB(assignment);
        codingManager.addCodingAssignmentToDB(assignment2);

        codingManager.addCodingQuestionToDB(question11);
        codingManager.addCodingQuestionToDB(question12);

        for (CodingQuestion question : questions2) {
            codingManager.addCodingQuestionToDB(question);
        }

        for (CodingAnswer answer : answers11) {
            codingManager.addCodingAnswerToDB(answer);
        }
        for (CodingAnswer answer : answers12) {
            codingManager.addCodingAnswerToDB(answer);
        }
        for (CodingAnswer answer : answers21) {
            codingManager.addCodingAnswerToDB(answer);
        }
        codingManager.addCodingAnswerToDB(answers22.get(0));
    }

    private void createQuizAssignment() {
        QuizQuestion question = new QuizQuestion("Which one is cat?");
        quizManager.addQuizQuestionToDB(question);
        QuizAnswer answer01 = new QuizAnswer("Dog", false, question);
        quizManager.addQuizAnswerToDB(answer01);
        QuizAnswer answer02 = new QuizAnswer("Cat", true, question);
        quizManager.addQuizAnswerToDB(answer02);

        QuizQuestion question01 = new QuizQuestion("Question 1 for assignment?");
        quizManager.addQuizQuestionToDB(question01);
        QuizQuestion question02 = new QuizQuestion("Question 2 for assignment?");
        quizManager.addQuizQuestionToDB(question02);
        List<QuizQuestion> questions = new ArrayList<>(Arrays.asList(question01, question02));
        Map<Skills, Integer> rewardMap = new HashMap<>();
        rewardMap.put(Skills.ALGORITHMS, 10);
        rewardMap.put(Skills.HTML_BASIC, 20);
        QuizAssignment quizAssignment = new QuizAssignment(
                "My first assignment",
                "Very cool assigment for everyone",
                rewardMap,
                10,
                questions
        );
        quizManager.addQuizAssignmentToDB(quizAssignment);
    }

    private void createMasteryAssignments() {
        Map<Skills, Integer> reward = new HashMap<>();
        reward.put(Skills.ALGORITHMS, 10);
        MasteryAssignment assignment = new MasteryAssignment("Name",
                "Description",
                reward,
                5,
                null,
                null);
        QuizQuestion question11 = new QuizQuestion("Quiz question");
        QuizQuestion question12 = new QuizQuestion("Quit question");
        CodingQuestion question21 = new CodingQuestion("Coding question");
        CodingQuestion question22 = new CodingQuestion("Modding question");
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        List<CodingQuestion> codingQuestions = new ArrayList<>();
        quizQuestions.add(question11);
        quizQuestions.add(question12);
        codingQuestions.add(question21);
        codingQuestions.add(question22);

        for (CodingQuestion question: codingQuestions) {
            codingManager.addCodingQuestionToDB(question);
        }
        for (QuizQuestion question: quizQuestions) {
            quizManager.addQuizQuestionToDB(question);
        }

        masteryManager.addMasteryAssignmentToDB(assignment);
    }
}
