package sample.quiz.model;

public interface Persistence {
    void writeUser(User u);
    User readUser(String username);
}
