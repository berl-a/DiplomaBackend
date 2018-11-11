package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.controller.simple_frontend_models.QuestionWithCategoryNames;
import system.dao.QuestionDao;
import system.model.questions.Question;
import system.model.questions.QuestionGroup;
import system.model.questions.QuestionGroupType;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    QuestionDao dao;
    @Autowired
    QuestionGroupService questionGroupService;

    private LinkedList<Question> cachedQuestions = new LinkedList<>();

    public void updateCached() {
        cachedQuestions = dao.getAll();
    }

    public LinkedList<Question> getAll() {
        updateCached();
        return cachedQuestions;
    }

    public Question get(String questionId) {
        Optional<Question> foundQuestion = getAll().stream().filter(q -> questionId.equals(q.getId())).findAny();
        return foundQuestion.orElse(null);
    }

    public String add(Question question) {
        updateCached();
        String result;
        dao.add(question);
        result = question.getId();
        return result;
    }

    public String copy(String id) {
        updateCached();
        String result;
        Question existingQuestion = get(id);
        Question copiedQuestion = new Question(existingQuestion);
        copiedQuestion.changeId();
        dao.add(copiedQuestion);
        result = copiedQuestion.getId();
        return result;
    }

    public String edit(Question question) {
        updateCached();
        dao.remove(question.getId());
        add(question);
        return Const.OK_RESULT;
    }

    public String remove(String id) {
        updateCached();
        System.out.println("Removing question with id " + id);
        dao.remove(id);
        return Const.OK_RESULT;
    }

    public List<QuestionWithCategoryNames> getAllWithCatNames() {
        updateCached();
        return appendCatNamesToQuestions(cachedQuestions);
    }

    public List<QuestionWithCategoryNames> getAllWithCatNamesByTeacher(String teacherId) {
        updateCached();
        LinkedList<Question> questionsByTeacher =
                cachedQuestions
                        .stream()
                        .filter(q -> teacherId.equals(q.getTeacher()))
                        .collect(Collectors.toCollection(LinkedList::new));
        return appendCatNamesToQuestions(questionsByTeacher);
    }

    private List<QuestionWithCategoryNames> appendCatNamesToQuestions(List<Question> questions) {
        List<QuestionWithCategoryNames> betterQuestions = new LinkedList<>();

        LinkedList<QuestionGroup> allGroups = questionGroupService.getAll();
        LinkedList<QuestionGroup>
                cats = new LinkedList<>(),
                subcats = new LinkedList<>(),
                subsubcats = new LinkedList<>();
        allGroups.forEach(g -> {
            if(g.getType() == QuestionGroupType.CAT) {
                cats.add(g);
            } else if(g.getType() == QuestionGroupType.SUBCAT) {
                subcats.add(g);
            } else if(g.getType() == QuestionGroupType.SUBSUBCAT) {
                subsubcats.add(g);
            }
        });

        for(Question q : questions) {
            QuestionGroup cat = cats.stream().filter(c -> c.getId().equals(q.getCategory())).findAny().orElse(null);
            QuestionGroup subcat = subcats.stream().filter(c -> c.getId().equals(q.getSubcategory())).findAny().orElse(null);
            QuestionGroup subsubcat = subsubcats.stream().filter(c -> c.getId().equals(q.getSubsubcategory())).findAny().orElse(null);
            //todo replace  == null ? ""  with default groups
            betterQuestions.add(new QuestionWithCategoryNames(q, cat == null ? "" : cat.getName(), subcat == null ? "" : subcat.getName(), subsubcat == null ? "" : subsubcat.getName()));
        }

        return betterQuestions;
    }
}
