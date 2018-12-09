package system.model.dao;

import org.springframework.stereotype.Repository;
import system.model.classes.quizzes.Quiz;

@Repository
public class QuizDao extends Dao<Quiz> {

    public QuizDao() {
        super("Quizzes");
    }

}
