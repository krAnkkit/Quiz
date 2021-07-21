package sample.quiz.driver;

import sample.quiz.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class QuizDriver {

    private static final Logger logger = Logger.getLogger("rank.sample.quiz");

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to our Quiz application");
        System.out.println("Enter your username : ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String username = br.readLine();
        User user = QuizMaster.getUser(username);
        List<Score> scores = user.getPastScores();
        Collections.sort(scores, new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                return o1.getQuizEndTime() < o2.getQuizEndTime() ? -1 : 1;
            }
        });
        if(!scores.isEmpty()) {
            System.out.println(String.format("Hello %s! You have already taken the quiz %d times", username, scores.size()));
            System.out.println("Past scores : ");
            for(Score s: scores) {
                System.out.println(s.toString());
            }
            System.out.println("Press R to retake the quiz");
            String input = br.readLine();
            if("R".equals(input)) {
                Quiz q = QuizMaster.create(user);
                runQuiz(q);
            }
        } else {
            System.out.println("You have not taken this quiz before. Press R to take the quiz.");
            String input = br.readLine();
            if("R".equals(input)) {
                Quiz q = QuizMaster.create(user);
                runQuiz(q);
            }
        }
    }

    private static void runQuiz(Quiz quiz) throws IOException {
        Question q = quiz.nextQuestion();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = 0;
        while(q!=null) {
            System.out.println(++i+ " "+q.getText());
            System.out.println(String.format("Choose from options:"));
            for(Question.Option o : q.options()) {
                System.out.println(o);
            }
            String in = br.readLine();
            // FIXME handle bad option
            Question.Option opt = Question.Option.valueOf(in);
            quiz.attempt(q,opt);
            q = quiz.nextQuestion();
        }
        Score score = quiz.submit();
        System.out.println("Your score : ");
        System.out.println(score.toString());
    }
}
