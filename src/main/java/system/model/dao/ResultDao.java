package system.model.dao;

import org.springframework.stereotype.Repository;
import system.model.classes.Result;

@Repository
public class ResultDao extends Dao<Result> {

    public ResultDao() {
        super("Results");
    }
}
