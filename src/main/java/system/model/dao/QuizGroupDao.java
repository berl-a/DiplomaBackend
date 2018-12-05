package system.model.dao;

import org.springframework.stereotype.Repository;
import system.model.quizzes.QuizGroup;

@Repository
public class QuizGroupDao extends Dao<QuizGroup> {

    public QuizGroupDao() {
        super("QuizGroups");
    }
}
