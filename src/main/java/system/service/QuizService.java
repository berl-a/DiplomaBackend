package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.GameDao;
import system.dao.QuizDao;
import system.model.games.Game;
import system.model.quizzes.Quiz;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    private LinkedList<Quiz> cachedQuizzes = new LinkedList<>();

    public void updateCachedQuizzes() {
        cachedQuizzes = quizDao.getQuizzes();
    }

    public LinkedList<Quiz> getQuizzes() {
        updateCachedQuizzes();
        return cachedQuizzes;
    }

    public Quiz getQuiz(String quizId) {
        Optional<Quiz> foundQuiz = getQuizzes().stream().filter(q -> quizId.equals(q.getId())).findAny();
        return foundQuiz.orElse(null);
    }
}
