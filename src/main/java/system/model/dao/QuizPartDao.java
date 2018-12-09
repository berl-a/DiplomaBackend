package system.model.dao;

import org.springframework.stereotype.Repository;
import system.model.classes.quizzes.QuizPart;

@Repository
public class QuizPartDao extends Dao<QuizPart> {

    public QuizPartDao() {
        super("QuizParts");
    }
}
