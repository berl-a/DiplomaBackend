package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.dao.QuizDao;
import system.model.quizzes.Quiz;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao dao;

    private LinkedList<Quiz> cachedQuizzes = new LinkedList<>();

    public void updateCached() {
        cachedQuizzes = dao.getAll();
    }

    public LinkedList<Quiz> getQuizzes() {
        updateCached();
        return cachedQuizzes;
    }

    public Quiz get(String quizId) {
        Optional<Quiz> foundQuiz = getQuizzes().stream().filter(q -> quizId.equals(q.getId())).findAny();
        return foundQuiz.orElse(null);
    }

    public String add(Quiz quiz) {
        updateCached();
        String result;
        dao.add(quiz);
        result = Const.OK_RESULT;
        return result;
    }

    public String edit(Quiz quiz) {
        updateCached();
        dao.remove(quiz.getId());
        add(quiz);
        return Const.OK_RESULT;
    }

    public String remove(String id) {
        updateCached();
        dao.remove(id);
        return Const.OK_RESULT;
    }
}
