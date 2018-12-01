package system.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.controller.simple_frontend_models.QuizWithCategoryNames;
import system.controller.dao.QuizDao;
import system.model.QuizGroupType;
import system.model.questions.Question;
import system.model.quizzes.Quiz;
import system.model.quizzes.QuizGroup;
import system.model.quizzes.QuizPart;

import java.util.Collections;
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
    @Autowired
    QuizPartService quizPartService;
    @Autowired
    QuestionGroupService questionGroupService;

    private LinkedList<Quiz> cachedQuizzes = new LinkedList<>();

    public void updateCached() {
        cachedQuizzes = dao.getAll();
    }

    public LinkedList<Quiz> getAll() {
        updateCached();
        return cachedQuizzes;
    }

    public Quiz get(String quizId) {
        Optional<Quiz> foundQuiz = getAll().stream().filter(q -> quizId.equals(q.getId())).findAny();
        return foundQuiz.orElse(null);
    }

    public Quiz appendQuizParts(Quiz quiz) {
        LinkedList<QuizPart> quizParts = new LinkedList<>(quizPartService.getAllFromQuiz(quiz.getId()));
        quiz.setParts(quizParts);
        return quiz;
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
//        System.out.println("Removing quiz with id " + id);
        dao.remove(id);
        return Const.OK_RESULT;
    }

    public void removeQuizzesFromGroup(String quizGroupId) {
        updateCached();
        LinkedList<Quiz> quizzesFromGroup = cachedQuizzes
                .stream()
                .filter(q ->
                        quizGroupId.equals(q.getCategory()) ||
                                quizGroupId.equals(q.getSubcategory()) ||
                                quizGroupId.equals(q.getSubsubcategory())
                )
                .collect(Collectors.toCollection(LinkedList::new));
        quizzesFromGroup.forEach(q -> remove(q.getId()));
    }

    public List<QuizWithCategoryNames> getAllWithCatNames() {
        updateCached();
        return appendCatNamesToQuizzes(cachedQuizzes);
    }

    public List<QuizWithCategoryNames> getAllWithCatNamesByTeacher(String teacherId) {
        updateCached();
        LinkedList<Quiz> quizzesByTeacher =
                cachedQuizzes
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

    public LinkedList<Question> generateRealQuestions(String quizId) {
        LinkedList<QuizPart> quizParts = appendQuizParts(get(quizId)).getParts();
        LinkedList<Question> questionsForPlayer = new LinkedList<>();
        for(QuizPart part : quizParts) {
            if(part.getCategory() != null && part.getCategory().equals(""))
                part.setCategory(null);
            if(part.getSubcategory() != null && part.getSubcategory().equals(""))
                part.setSubcategory(null);
            if(part.getSubsubcategory() != null && part.getSubsubcategory().equals(""))
                part.setSubsubcategory(null);
            LinkedList<Question> questionsFromThisPart = questionGroupService.getQuestionsFromGroupsFree(part.getCategory(), part.getSubcategory(), part.getSubsubcategory());
            System.out.println("Part: " + part.getName() + " " + part.getNumber());
            System.out.println("but number of questions from this part: " + questionsFromThisPart.size());
            Collections.shuffle(questionsFromThisPart);
            questionsForPlayer.addAll(questionsFromThisPart.subList(0, part.getNumber()));
        }
        Collections.shuffle(questionsForPlayer);
        questionsForPlayer = questionsForPlayer.stream().peek(q -> q.setCorrectAnswers(null)).collect(Collectors.toCollection(LinkedList::new));
        return questionsForPlayer;
    }
}
