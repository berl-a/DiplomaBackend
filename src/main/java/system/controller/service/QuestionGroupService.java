package system.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.controller.Const;
import system.controller.simple_frontend_models.QuestionGroupWithNumberOfQuestions;
import system.controller.dao.QuestionGroupDao;
import system.model.questions.Question;
import system.model.questions.QuestionGroup;
import system.model.questions.QuestionGroupType;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionGroupService {
    @Autowired
    QuestionGroupDao dao;

    @Autowired
    QuestionService questionService;

    private LinkedList<QuestionGroup> cachedQuestionGroups = new LinkedList<>();

    public void updateCached() {
        cachedQuestionGroups = dao.getAll();
    }

    public LinkedList<QuestionGroup> getAll() {
        updateCached();
        return cachedQuestionGroups;
    }

    public List<QuestionGroupWithNumberOfQuestions> getAllWithNumberOfQuestions() {
        List<QuestionGroupWithNumberOfQuestions> betterGroups = new LinkedList<>();
        LinkedList<QuestionGroup> allGroups = getAll();
        allGroups.forEach(gr -> betterGroups.add(new QuestionGroupWithNumberOfQuestions(gr, getNumberOfQuestions(gr))));
        return betterGroups;
    }

    public LinkedList<QuestionGroup> getAll(String teacherId) {
        updateCached();
        return cachedQuestionGroups.stream().filter(gr -> teacherId.equals(gr.getTeacher())).collect(Collectors.toCollection(LinkedList::new));
    }

    public List<QuestionGroupWithNumberOfQuestions> getAllWithNumberOfQuestions(String teacherId) {
        List<QuestionGroupWithNumberOfQuestions> betterGroups = new LinkedList<>();
        LinkedList<QuestionGroup> allGroups = getAll();
        allGroups.forEach(gr -> {
            if(teacherId.equals(gr.getTeacher())) betterGroups.add(new QuestionGroupWithNumberOfQuestions(gr, getNumberOfQuestions(gr)));
        });
        return betterGroups;
    }

    public QuestionGroup get(String id) {
        Optional<QuestionGroup> foundQuestionGroup = getAll().stream().filter(q -> id.equals(q.getId())).findAny();
        return foundQuestionGroup.orElse(null);
    }

    public String add(QuestionGroup q) {
        updateCached();
        dao.add(q);
        return q.getId();
    }

    public String edit(QuestionGroup q) {
        updateCached();
        dao.remove(q.getId());
        dao.add(q);
        return Const.OK_RESULT;
    }

    public String remove(String id) {
        updateCached();
        dao.remove(id);
        return Const.OK_RESULT;
    }



    public int getNumberOfQuestions(QuestionGroup questionGroup) {
//        System.out.println("Question group type is " + questionGroup.getType());
        if(questionGroup.getType() == QuestionGroupType.CAT) return getNumberOfQuestions(questionGroup.getId(), null, null);
        else if(questionGroup.getType() == QuestionGroupType.SUBCAT) return getNumberOfQuestions(questionGroup.getFather(), questionGroup.getId(), null);
        else return getNumberOfQuestions(questionGroup.getGrandfather(), questionGroup.getFather(), questionGroup.getId());
    }

    public String rename(String id, String newName) {
        updateCached();
        QuestionGroup group = get(id);
        dao.remove(id);
        group.setName(newName);
        dao.add(group);
        return Const.OK_RESULT;
    }

    public int getNumberOfQuestions(String cat, String subcat, String subsubcat) {
        return getQuestionsFromGroupsFree(cat, subcat, subsubcat).size();
    }

    public LinkedList<Question> getQuestionsFromGroups(String cat, String subcat, String subsubcat) {
        LinkedList<Question> selectedQuestions = new LinkedList<>();

        LinkedList<Question> questions = questionService.getAll();
        boolean catNotNull = (cat != null);
        boolean subcatNotNull = (subcat != null);
        boolean subsubcatNotNull = (subsubcat != null);
//        System.out.println("Looking for questions with the same setup:");
//        System.out.println("Cat is " + cat + ", subcat is " + subcat + ", subsubcat is " + subsubcat);
        selectedQuestions = questions.stream().filter(q -> {
            q.removeEmptyGroups();
//            System.out.println(q.getCategory() + " " + q.getSubsubcategory() + " " + q.getSubsubcategory());
            if(catNotNull) {
                if(subcatNotNull) {
                    if(subsubcatNotNull) {
                        return cat.equals(q.getCategory()) && subcat.equals(q.getSubcategory()) && subsubcat.equals(q.getSubsubcategory());
                    } else {
                        return cat.equals(q.getCategory()) && subcat.equals(q.getSubcategory()) && q.getSubsubcategory() == null;
                    }
                } else {
                    return cat.equals(q.getCategory()) && q.getSubcategory() == null;
                }
            } else {
                return q.getCategory() == null;
            }
        }).collect(Collectors.toCollection(LinkedList::new));
//        System.out.println("Questions from group: " + selectedQuestions.size() + " of them");
        return selectedQuestions;
    }


    public LinkedList<Question> getQuestionsFromGroupsFree(String cat, String subcat, String subsubcat) {
        LinkedList<Question> selectedQuestions = new LinkedList<>();

        LinkedList<Question> questions = questionService.getAll();
        boolean catNotNull = (cat != null);
        boolean subcatNotNull = (subcat != null);
        boolean subsubcatNotNull = (subsubcat != null);
//        System.out.println("Looking for questions with the same setup:");
//        System.out.println("Cat is " + cat + ", subcat is " + subcat + ", subsubcat is " + subsubcat);
        selectedQuestions = questions.stream().filter(q -> {
            q.removeEmptyGroups();
//            System.out.println(q.getCategory() + " " + q.getSubsubcategory() + " " + q.getSubsubcategory());

            if(catNotNull) {
                if(subcatNotNull) {
                    if(subsubcatNotNull) {
                        return cat.equals(q.getCategory()) && subcat.equals(q.getSubcategory()) && subsubcat.equals(q.getSubsubcategory());
                    } else {
                        return cat.equals(q.getCategory()) && subcat.equals(q.getSubcategory());
                    }
                } else {
                    return cat.equals(q.getCategory());
                }
            } else {
                return true;
            }
        }).collect(Collectors.toCollection(LinkedList::new));

        System.out.println("Questions from group: " + selectedQuestions.size() + " of them");
        return selectedQuestions;
    }

//    public LinkedList<String> getQuestionsInGroup(String groupId) {
//        updateCached();
//        Optional<QuestionGroup> foundGroup = cachedQuestionGroups.stream().filter(gr -> groupId.equals(gr.getId())).findAny();
//        return foundGroup.isPresent() ? foundGroup.getWithQuiz().getQuestionsFromGroups() : new LinkedList<>();
//    }

}
