package sample.quiz.model.impl;

import sample.quiz.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FixedQuiz implements Quiz {

    private User user;
    private List<StaticQuestion> questions;
    private Persistence pers;

    private Iterator<StaticQuestion> iter;


    public FixedQuiz(User u, Persistence pers) {
        this.user = u;
        this.pers = pers;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(StaticQuestion q) {
        questions.add(q);
    }

    @Override
    public void attempt(Question q, Question.Option opt) {
        StaticQuestion sq = (StaticQuestion) q;
        sq.setUserSubmitted(opt);
    }

    public Score submit() {
        Score sc = computeScore();
        user.addScore(sc);
        pers.writeUser(user);
        return sc;
    }

    @Override
    public Question nextQuestion() {
        if(iter == null) {
            iter = questions.iterator();
        }
        return iter.hasNext() ? iter.next(): null;
    }

    private Score computeScore() {
        Score sc = new Score(user.getId());
        sc.totalQuestions = questions.size();
        for(StaticQuestion q: questions) {
            if(q.getUserSubmitted() == Question.Option.SKIP) {
                sc.skipped++;
                continue;
            } else if (q.getUserSubmitted() == q.getCorrect()){
                sc.correct++;
            } else {
                sc.incorrect++;
            }
        }
        return sc;
    }

}
