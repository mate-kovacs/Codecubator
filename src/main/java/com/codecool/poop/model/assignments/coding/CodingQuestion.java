package com.codecool.poop.model.assignments.coding;

public class CodingQuestion {

    private String instruction;
    private String question;
    private CodingAnswer answer;

    public CodingQuestion(String instruction, String question) {
        this.instruction = instruction;
        this.question = question;
    }

    public Integer correctSolutions(CodingAnswer currentAnswer){
        return answer.matchingSolutions(currentAnswer);
    }

    public int getMaxPoints() {
        return answer.getAnswers().size();
    }

    public CodingAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(CodingAnswer answer) {
        this.answer = answer;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getQuestions() {
        return question;
    }

    @Override
    public String toString() {
        return "Your task: " + instruction +
                "\n" + question;
    }
}
