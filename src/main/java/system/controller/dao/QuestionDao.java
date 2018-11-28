package system.controller.dao;

import org.springframework.stereotype.Repository;
import system.model.questions.Question;

@Repository
public class QuestionDao extends Dao<Question> {

    public QuestionDao() {
        super("Questions");
    }
}
