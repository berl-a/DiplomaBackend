package system.model.quizzes;

import system.model.questions.IQuestion;

import java.util.LinkedList;

public class Quiz {

    private String id;
    private String name;
    private String teacher;
    private long time;

    LinkedList<QuizPart> parts = new LinkedList<>();

    public Quiz() {
    }

    public LinkedList<QuizPart> getParts() {
        return parts;
    }

    public void setParts(LinkedList<QuizPart> parts) {
        this.parts = parts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
