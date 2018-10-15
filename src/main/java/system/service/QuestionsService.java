package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.QuestionsDao;
import system.model.IQuestion;

import java.util.LinkedList;

@Service
public class QuestionsService {

    @Autowired
    QuestionsDao questionsDao;

    private LinkedList<IQuestion> cachedQuestions = new LinkedList<>();

    public void updateCachedQuestions() {
        cachedQuestions = questionsDao.getQuestions();
    }

}
