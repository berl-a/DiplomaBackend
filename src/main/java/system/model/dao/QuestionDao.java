package system.model.dao;

import org.springframework.stereotype.Repository;
import system.model.classes.questions.Question;

@Repository
public class QuestionDao extends Dao<Question> {

    public QuestionDao() {
        super("Questions");
    }
}
