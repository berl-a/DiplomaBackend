package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.QuestionDao;
import system.model.questions.Question;

import java.util.LinkedList;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    private LinkedList<Question> cachedQuestions = new LinkedList<>();

    public void updateCachedQuestions() {
        cachedQuestions = questionDao.getAll();
    }

    public LinkedList<Question> getQuestions() {
        updateCachedQuestions();
        return cachedQuestions;
    }

}
