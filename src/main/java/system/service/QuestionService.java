package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.QuestionDao;
import system.model.questions.IQuestion;

import java.util.LinkedList;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    private LinkedList<IQuestion> cachedQuestions = new LinkedList<>();

    public void updateCachedQuestions() {
        cachedQuestions = questionDao.getQuestions();
    }

    public LinkedList<IQuestion> getQuestions() {
        updateCachedQuestions();
        return cachedQuestions;
    }

}
