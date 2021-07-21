package sample.quiz.model;

import java.util.List;

public interface Question {
    public enum Option {
        A, B, C, D, SKIP;
    }
    String getText();
    List<Option> options();

    /*
    void submit(Option opt);
    int getScore();
    */
}
