package system.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.controller.simple_frontend_models.QuizPartWithCategoryNames;
import system.model.dao.QuizPartDao;
import system.model.questions.QuestionGroup;
import system.model.questions.QuestionGroupType;
import system.model.quizzes.QuizPart;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizPartService {

    @Autowired
    QuizPartDao dao;
    @Autowired
    QuestionGroupService questionGroupService;

    private LinkedList<QuizPart> cached = new LinkedList<>();

    public void updateCached() {
        cached = dao.getAll();
    }

    public LinkedList<QuizPart> getAll() {
        updateCached();
        return cached;
    }

    public QuizPart get(String quizPartId) {
        Optional<QuizPart> foundQuizPart = getAll().stream().filter(q -> quizPartId.equals(q.getId())).findAny();
        return foundQuizPart.orElse(null);
    }

    public String add(QuizPart quizPart) {
        updateCached();
        String result;
        dao.add(quizPart);
        result = quizPart.getId();
        return result;
    }

    public String edit(QuizPart quizPart) {
        updateCached();
        dao.remove(quizPart.getId());
        add(quizPart);
        return Const.OK_RESULT;
    }

    public String remove(String id) {
        updateCached();
        dao.remove(id);
        return Const.OK_RESULT;
    }

    public List<QuizPart> getAllFromQuiz(String quizId) {
        updateCached();
        LinkedList<QuizPart> quizPartsFromQuiz =
                cached
                        .stream()
                        .filter(q -> q != null && quizId.equals(q.getQuiz()))
                        .collect(Collectors.toCollection(LinkedList::new));
        return quizPartsFromQuiz;
    }


    public String copy(String id) {
        updateCached();
        String result;
        QuizPart existingQuizPart = get(id);
        QuizPart copiedQuizPart = new QuizPart(existingQuizPart);
        copiedQuizPart.changeId();
        dao.add(copiedQuizPart);
        result = copiedQuizPart.getId();
        return result;
    }

    public List<QuizPartWithCategoryNames> getAllWithCatNamesFromQuiz(String quizId) {
        updateCached();
        LinkedList<QuizPart> quizzesByTeacher =
                cached
                        .stream()
                        .filter(q -> q != null && quizId.equals(q.getQuiz()))
                        .collect(Collectors.toCollection(LinkedList::new));
        return appendCatNamesToQuizParts(quizzesByTeacher);
    }

    private List<QuizPartWithCategoryNames> appendCatNamesToQuizParts(List<QuizPart> quizParts) {
        List<QuizPartWithCategoryNames> betterQuizParts = new LinkedList<>();

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

        for(QuizPart q : quizParts) {
            QuestionGroup cat = cats.stream().filter(c -> c.getId().equals(q.getCategory())).findAny().orElse(null);
            QuestionGroup subcat = subcats.stream().filter(c -> c.getId().equals(q.getSubcategory())).findAny().orElse(null);
            QuestionGroup subsubcat = subsubcats.stream().filter(c -> c.getId().equals(q.getSubsubcategory())).findAny().orElse(null);
            //todo replace  == null ? ""  with default groups
            betterQuizParts.add(new QuizPartWithCategoryNames(
                    q,
                    cat == null ? "" : cat.getName(),
                    subcat == null ? "" : subcat.getName(),
                    subsubcat == null ? "" : subsubcat.getName()
            ));
        }

        return betterQuizParts;
    }
}
