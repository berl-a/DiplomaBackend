package system.model.quizzes;

import java.util.LinkedList;

public class QuizGroup {

    private String id;
    private LinkedList<String> quizzes = new LinkedList<>();
    private String teacher;

    private String father;
    private String grandfather;
    private String name;

    public QuizGroup(String id, String teacher, String father, String grandfather, String name) {
        this.id = id;
        this.teacher = teacher;
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

    public LinkedList<String> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(LinkedList<String> quizzes) {
        this.quizzes = quizzes;
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
