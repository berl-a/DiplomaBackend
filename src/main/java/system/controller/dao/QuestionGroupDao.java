package system.controller.dao;

import org.springframework.stereotype.Repository;
import system.model.questions.QuestionGroup;

@Repository
public class QuestionGroupDao extends Dao<QuestionGroup> {

    public QuestionGroupDao() {
        super("QuestionGroups");
    }
}
