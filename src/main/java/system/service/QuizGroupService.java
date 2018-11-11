package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.dao.QuizGroupDao;
import system.model.questions.QuestionGroup;
import system.model.quizzes.QuizGroup;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class QuizGroupService {

    @Autowired
    QuizGroupDao dao;

    private LinkedList<QuizGroup> cachedQuizGroups = new LinkedList<>();

    public void updateCached() {
        cachedQuizGroups = dao.getAll();
    }

    public LinkedList<QuizGroup> getAll() {
        updateCached();
        return cachedQuizGroups;
    }

    public QuizGroup get(String quizId) {
        Optional<QuizGroup> foundQuizGroup = getAll().stream().filter(q -> quizId.equals(q.getId())).findAny();
        return foundQuizGroup.orElse(null);
    }

    public String add(QuizGroup quiz) {
        updateCached();
        dao.add(quiz);
        return quiz.getId();
    }

    public String edit(QuizGroup quiz) {
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


    public String rename(String id, String newName) {
        updateCached();
        QuizGroup group = get(id);
        dao.remove(id);
        group.setName(newName);
        dao.add(group);
        return Const.OK_RESULT;
    }

//    public LinkedList<String> getQuizsInGroup(String groupId) {
//        updateCached();
//        Optional<QuizGroup> foundGroup = cachedQuizGroups.stream().filter(gr -> groupId.equals(gr.getId())).findAny();
//        return foundGroup.isPresent() ? foundGroup.get().getQuizs() : new LinkedList<>();
//    }

}
