package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.dao.QuestionGroupDao;
import system.model.questions.QuestionGroup;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class QuestionGroupService {

    @Autowired
    QuestionGroupDao dao;

    private LinkedList<QuestionGroup> cachedQuestionGroups = new LinkedList<>();

    public void updateCached() {
        cachedQuestionGroups = dao.getAll();
    }

    public LinkedList<QuestionGroup> getAll() {
        updateCached();
        return cachedQuestionGroups;
    }

    public QuestionGroup get(String quizId) {
        Optional<QuestionGroup> foundQuestionGroup = getAll().stream().filter(q -> quizId.equals(q.getId())).findAny();
        return foundQuestionGroup.orElse(null);
    }

    public String add(QuestionGroup quiz) {
        updateCached();
        dao.add(quiz);
        return quiz.getId();
    }

    public String edit(QuestionGroup quiz) {
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
    
//    public LinkedList<String> getQuestionsInGroup(String groupId) {
//        updateCached();
//        Optional<QuestionGroup> foundGroup = cachedQuestionGroups.stream().filter(gr -> groupId.equals(gr.getId())).findAny();
//        return foundGroup.isPresent() ? foundGroup.get().getQuestions() : new LinkedList<>();
//    }

}
