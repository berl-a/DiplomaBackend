package system.dao;

import org.springframework.stereotype.Repository;
import system.model.quizzes.Quiz;
import system.model.quizzes.QuizPart;

@Repository
public class QuizPartDao extends Dao<QuizPart> {

    public QuizPartDao() {
        super("QuizParts");
    }
}
