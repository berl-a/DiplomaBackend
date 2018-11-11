package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.controller.simple_frontend_models.QuizWithCategoryNames;
import system.dao.QuizDao;
import system.model.QuizGroupType;
import system.model.quizzes.Quiz;
import system.model.quizzes.QuizGroup;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    QuizDao dao;
    @Autowired
    QuizGroupService quizGroupService;

    private LinkedList<Quiz> cachedQuizs = new LinkedList<>();

    public void updateCached() {
        cachedQuizs = dao.getAll();
    }

    public LinkedList<Quiz> getAll() {
        updateCached();
        return cachedQuizs;
    }

    public Quiz get(String quizId) {
        Optional<Quiz> foundQuiz = getAll().stream().filter(q -> quizId.equals(q.getId())).findAny();
        return foundQuiz.orElse(null);
    }

    public String add(Quiz quiz) {
        updateCached();
        String result;
        dao.add(quiz);
        result = quiz.getId();
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
        System.out.println("Removing quiz with id " + id);
        dao.remove(id);
        return Const.OK_RESULT;
    }

    public List<QuizWithCategoryNames> getAllWithCatNames() {
        updateCached();
        return appendCatNamesToQuizzes(cachedQuizs);
    }

    public List<QuizWithCategoryNames> getAllWithCatNamesByTeacher(String teacherId) {
        updateCached();
        LinkedList<Quiz> quizzesByTeacher =
                cachedQuizs
                        .stream()
                        .filter(q -> teacherId.equals(q.getTeacher()))
                        .collect(Collectors.toCollection(LinkedList::new));
        return appendCatNamesToQuizzes(quizzesByTeacher);
    }

    private List<QuizWithCategoryNames> appendCatNamesToQuizzes(List<Quiz> quizs) {
        List<QuizWithCategoryNames> betterQuizs = new LinkedList<>();

        LinkedList<QuizGroup> allGroups = quizGroupService.getAll();
        LinkedList<QuizGroup>
                cats = new LinkedList<>(),
                subcats = new LinkedList<>(),
                subsubcats = new LinkedList<>();
        allGroups.forEach(g -> {
            if(g.getType() == QuizGroupType.CAT) {
                cats.add(g);
            } else if(g.getType() == QuizGroupType.SUBCAT) {
                subcats.add(g);
            } else if(g.getType() == QuizGroupType.SUBSUBCAT) {
                subsubcats.add(g);
            }
        });

        for(Quiz q : quizs) {
            QuizGroup cat = cats.stream().filter(c -> c.getId().equals(q.getCategory())).findAny().orElse(null);
            QuizGroup subcat = subcats.stream().filter(c -> c.getId().equals(q.getSubcategory())).findAny().orElse(null);
            QuizGroup subsubcat = subsubcats.stream().filter(c -> c.getId().equals(q.getSubsubcategory())).findAny().orElse(null);
            //todo replace  == null ? ""  with default groups
            betterQuizs.add(new QuizWithCategoryNames(q, cat == null ? "" : cat.getName(), subcat == null ? "" : subcat.getName(), subsubcat == null ? "" : subsubcat.getName()));
        }

        return betterQuizs;
    }

    public String copy(String id) {
        updateCached();
        String result;
        Quiz existingQuiz = get(id);
        Quiz copiedQuiz = new Quiz(existingQuiz);
        copiedQuiz.changeId();
        dao.add(copiedQuiz);
        result = copiedQuiz.getId();
        return result;
    }
}
