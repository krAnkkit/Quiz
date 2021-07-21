package sample.quiz.model;

import sample.quiz.model.impl.DiskPersistence;
import sample.quiz.model.impl.FixedQuiz;
import sample.quiz.model.impl.StaticQuestion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class QuizMaster {

    private static final QuizMaster INSTANCE = new QuizMaster();

    private Persistence pers;

    private QuizMaster() {
        pers = new DiskPersistence();
    }

    public static Quiz create(User u) {
        FixedQuiz q = new FixedQuiz(u, INSTANCE.pers);
        for(int i=0; i<5; i++) {
            q.addQuestion(new StaticQuestion("Random question " + i+1, Question.Option.A));
        }
        return q;
    }

    public static User getUser(String userId) {
        return INSTANCE.pers.readUser(userId);
    }

}
