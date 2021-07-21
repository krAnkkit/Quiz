package sample.quiz.model;

import java.time.Instant;
import java.util.Date;

public class Score {
    private String userId;
    private long quizEndTime;
    // Score attributes
    public int totalQuestions;
    public int attempted;
    public int skipped;
    public int correct;
    public int incorrect;

    public Score(String userID) {
        this.userId = userID;
        this.quizEndTime = System.currentTimeMillis();
    }

    public long getQuizEndTime() {
        return quizEndTime;
    }

    @Override
    public String toString() {
        return String.format("Submitted at: %s, Questions: %d, Attempted: %d, Skipped: %d, Correct: %d, Incorrect: %d", Date.from(Instant.ofEpochMilli(quizEndTime)), totalQuestions, attempted, skipped, correct, incorrect);
    }
}
