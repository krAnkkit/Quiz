package sample.quiz.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private List<Score> pastScores = new ArrayList<>();

    public User(String uid) {
        this.id = uid;
    }

    public String getId() {
        return id;
    }

    public List<Score> getPastScores() {
        // We return a copy to make sure the score list is not modifiable
        return new ArrayList<>(pastScores);
    }

    public void addScore(Score sc) {
        pastScores.add(sc);
    }
}
