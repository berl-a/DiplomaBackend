package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.QuestionDao;
import system.dao.QuestionGroupDao;
import system.model.questions.IQuestion;
import system.model.questions.QuestionGroup;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class QuestionGroupService {

    @Autowired
    QuestionGroupDao questionGroupDao;

    private LinkedList<QuestionGroup> cachedQuestionGroups = new LinkedList<>();

    public void updateCachedQuestionGroup() {
        cachedQuestionGroups = questionGroupDao.getQuestionGroups();
    }

    public LinkedList<QuestionGroup> getGroups() {
        updateCachedQuestionGroup();
        return cachedQuestionGroups;
    }

    public LinkedList<String> getQuestionsInGroup(String groupId) {
        updateCachedQuestionGroup();
        Optional<QuestionGroup> foundGroup = cachedQuestionGroups.stream().filter(gr -> groupId.equals(gr.getId())).findAny();
        return foundGroup.isPresent() ? foundGroup.get().getQuestions() : new LinkedList<>();
    }

}
