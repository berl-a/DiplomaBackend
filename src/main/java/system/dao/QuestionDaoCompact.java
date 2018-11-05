package system.dao;

import org.springframework.stereotype.Repository;
import system.model.questions.Question;

@Repository
public class QuestionDaoCompact extends Dao<Question> {

    public QuestionDaoCompact() {
        super("Questions");
    }
}
