package system.dao;

import org.springframework.stereotype.Repository;
import system.model.questions.QuestionGroup;

@Repository
public class QuestionGroupDaoCompact extends Dao<QuestionGroup> {

    public QuestionGroupDaoCompact() {
        super("QuestionGroups");
    }
}
