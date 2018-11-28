package system.model.quizzes;

import system.controller.tools.DataToolkit;
import system.controller.dao.Idable;
import system.model.QuizGroupType;

import java.io.Serializable;

public class QuizGroup implements Serializable, Idable {

    private String id;
    private String teacher;

    private QuizGroupType type;

    private String father;
    private String grandfather;
    private String name;

    public QuizGroup() {
        this.id = DataToolkit.getUniqueId();
    }

    public QuizGroup(String id, String teacher, String father, String grandfather, String name, QuizGroupType type) {
        this.id = id;
        this.teacher = teacher;
        this.father = father;
        this.grandfather = grandfather;
        this.name = name;
        this.type = type;
    }

    public QuizGroup(String id, String teacher, QuizGroupType type, String father, String grandfather, String name) {
        this.id = id;
        this.teacher = teacher;
        this.type = type;
        this.father = father;
        this.grandfather = grandfather;
        this.name = name;
    }

    public QuizGroup(String teacher, QuizGroupType type, String father, String grandfather, String name) {
        this.id = DataToolkit.getUniqueId();
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

    public QuizGroupType getType() {
        return type;
    }

    public void setType(QuizGroupType type) {
        this.type = type;
    }
}
