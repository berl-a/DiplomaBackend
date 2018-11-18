package system.dao;

import org.springframework.stereotype.Repository;
import system.model.Result;
import system.model.quizzes.Quiz;

@Repository
public class ResultDao extends Dao<Result> {

    public ResultDao() {
        super("Results");
    }
}
