package system.model.classes.questions;

import system.controller.tools.DataToolkit;
import system.model.dao.Idable;

import java.io.Serializable;

public class QuestionGroup implements Serializable, Idable {

    private String id;
    private String teacher;

    private QuestionGroupType type;
    private String father;
    private String grandfather;
    private String name;

    public QuestionGroup() {
        this.id = DataToolkit.getUniqueId();
    }

    public QuestionGroup(String id) {
        this.id = id;
    }

    public QuestionGroup(String teacher, QuestionGroupType type, String father, String grandfather, String name) {
        this.id = DataToolkit.getUniqueId();
        this.teacher = teacher;
        this.type = type;
        this.father = father;
        this.grandfather = grandfather;
        this.name = name;
    }

    public QuestionGroup(String id, String teacher, QuestionGroupType type, String father, String grandfather, String name) {
        this.id = id;
        this.teacher = teacher;
        this.type = type;
        this.father = father;
        this.grandfather = grandfather;
        this.name = name;
    }

    public QuestionGroup(QuestionGroup q) {
        this.id = q.id;
        this.teacher = q.teacher;
        this.type = q.type;
        this.father = q.father;
        this.grandfather = q.grandfather;
        this.name = q.name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

//    public LinkedList<String> getQuestionsFromGroups() {
//        return questions;
//    }
//
//    public void setQuestions(LinkedList<String> questions) {
//        this.questions = questions;
//    }


    public QuestionGroupType getType() {
        return type;
    }

    public void setType(QuestionGroupType type) {
        this.type = type;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getGrandfather() {
        return grandfather;
    }

    public void setGrandfather(String grandfather) {
        this.grandfather = grandfather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
