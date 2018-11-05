package system.dao;

import org.springframework.stereotype.Repository;
import system.model.quizzes.Quiz;

@Repository
public class QuizDaoCompact extends Dao<Quiz> {

    public QuizDaoCompact() {
        super("Quizzes");
    }
}
