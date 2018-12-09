package system.model.classes.quizzes;

import system.controller.tools.DataToolkit;
import system.model.dao.Idable;

import java.io.Serializable;
import java.util.LinkedList;

public class Quiz implements Serializable, Idable {

    private String id;
    private String name;
    private String teacher;
    private String category, subcategory, subsubcategory;

    private long time;

    LinkedList<QuizPart> parts = new LinkedList<>();

    public Quiz() {
        this.id = DataToolkit.getUniqueId();
    }

    public Quiz(Quiz q) {
        this.id = q.id;
        this.name = q.name;
        this.teacher = q.teacher;
        this.category = q.category;
        this.subcategory = q.subcategory;
        this.subsubcategory = q.subsubcategory;
        this.time = q.time;
        this.parts = q.parts;
    }

    public Quiz(String id, String name, String teacher, String category, String subcategory, String subsubcategory, long time, LinkedList<QuizPart> parts) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.category = category;
        this.subcategory = subcategory;
        this.subsubcategory = subsubcategory;
        this.time = time;
        this.parts = parts;
    }

    public Quiz(String name, String teacher, String category, String subcategory, String subsubcategory, long time, LinkedList<QuizPart> parts) {
        this.id = DataToolkit.getUniqueId();
        this.name = name;
        this.teacher = teacher;
        this.category = category;
        this.subcategory = subcategory;
        this.subsubcategory = subsubcategory;
        this.time = time;
        this.parts = parts;
    }

    public Quiz(String id, String name, String teacher, String category, String subcategory, String subsubcategory) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.category = category;
        this.subcategory = subcategory;
        this.subsubcategory = subsubcategory;
    }

    public Quiz(String name, String teacher, String category, String subcategory, String subsubcategory) {
        this.id = DataToolkit.getUniqueId();
        this.name = name;
        this.teacher = teacher;
        this.category = category;
        this.subcategory = subcategory;
        this.subsubcategory = subsubcategory;
    }

    public String changeId() {
        this.id = DataToolkit.getUniqueId();
        return this.id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getSubsubcategory() {
        return subsubcategory;
    }

    public void setSubsubcategory(String subsubcategory) {
        this.subsubcategory = subsubcategory;
    }
}
