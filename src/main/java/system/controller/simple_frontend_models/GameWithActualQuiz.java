package system.controller.simple_frontend_models;

import system.model.classes.games.Game;
import system.model.classes.quizzes.Quiz;

/**
 * Game with quiz attached
 */
public class GameWithActualQuiz extends Game {

    private Quiz actualQuiz;

    public GameWithActualQuiz(Game g, Quiz quiz) {
        super(g);
        this.actualQuiz = quiz;
    }

    public Quiz getActualQuiz() {
        return actualQuiz;
    }

    public void setActualQuiz(Quiz actualQuiz) {
        this.actualQuiz = actualQuiz;
    }
}
