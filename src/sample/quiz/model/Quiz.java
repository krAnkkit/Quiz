package sample.quiz.model;

import java.util.List;

public interface Quiz {

    public void attempt(Question q, Question.Option opt);

    public Score submit();

    public Question nextQuestion();

}
