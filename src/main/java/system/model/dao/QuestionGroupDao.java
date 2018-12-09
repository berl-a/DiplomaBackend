package system.model.dao;

import org.springframework.stereotype.Repository;
import system.model.classes.questions.QuestionGroup;

@Repository
public class QuestionGroupDao extends Dao<QuestionGroup> {

    public QuestionGroupDao() {
        super("QuestionGroups");
    }
}
