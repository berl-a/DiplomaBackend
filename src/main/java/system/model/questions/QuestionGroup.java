package system.model.questions;

import system.dao.Idable;

public class QuestionGroup implements Idable {

    private String id;
//    private LinkedList<String> questions = new LinkedList<>();
    private String teacher;

    private QuestionGroupType type;
    private String father;
    private String grandfather;
    private String name;

    public QuestionGroup(String id, String teacher, QuestionGroupType type, String father, String grandfather, String name) {
        this.id = id;
        this.teacher = teacher;
        this.type = type;
        this.father = father;
        this.grandfather = grandfather;
        this.name = name;
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

//    public LinkedList<String> getQuestions() {
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
