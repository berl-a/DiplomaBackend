package system.dao;

import system.model.questions.Question;

public class QuestionDaoCompact extends Dao<Question> {

    public QuestionDaoCompact() {
        super("Questions");
    }
}
