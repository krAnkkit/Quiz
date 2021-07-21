package sample.quiz.model.impl;

import sample.quiz.model.Question;

import java.util.Arrays;
import java.util.List;

public class StaticQuestion implements Question {

    private String text;
    private Option correct;
    private Option userSubmitted;

    public StaticQuestion(String text, Question.Option correct) {
        this.text = text;
        this.correct = correct;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public List<Option> options() {
        return Arrays.asList(Option.A, Option.B, Option.C, Option.D, Option.SKIP);
    }

    public void setUserSubmitted(Option opt) {
        this.userSubmitted = opt;
    }

    public Option getUserSubmitted() {
        return userSubmitted;
    }

    public Option getCorrect() {
        return correct;
    }
}
