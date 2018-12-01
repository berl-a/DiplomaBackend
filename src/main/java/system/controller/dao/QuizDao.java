package system.controller.dao;

import org.springframework.stereotype.Repository;
import system.model.quizzes.Quiz;

import java.util.LinkedList;

@Repository
public class QuizDao extends Dao<Quiz> {

    public QuizDao() {
        super("Quizzes");
    }

}
