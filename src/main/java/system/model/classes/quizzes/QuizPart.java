package system.model.classes.quizzes;

import system.controller.tools.DataToolkit;
import system.model.dao.Idable;

import java.io.Serializable;

public class QuizPart implements Serializable, Idable {

    private String id;
    private String name;
    private String quiz;
    private int order;
    private int number;
    private String category, subcategory, subsubcategory;

    public QuizPart() {
        this.id = DataToolkit.getUniqueId();
    }

    public QuizPart(String id, String name, String quiz, int order, int number, String category, String subcategory, String subsubcategory) {
        this.id = id;
        this.name = name;
        this.quiz = quiz;
        this.order = order;
        this.number = number;
        this.category = category;
        this.subcategory = subcategory;
        this.subsubcategory = subsubcategory;
    }

    public QuizPart(String name, String quiz, int order, int number, String category, String subcategory, String subsubcategory) {
        this.id = DataToolkit.getUniqueId();
        this.name = name;
        this.quiz = quiz;
        this.order = order;
        this.number = number;
        this.category = category;
        this.subcategory = subcategory;
        this.subsubcategory = subsubcategory;
    }

    public QuizPart(QuizPart q) {
        this.id = q.id;
        this.name = q.name;
        this.quiz = q.quiz;
        this.order = q.order;
        this.number = q.number;
        this.category = q.category;
        this.subcategory = q.subcategory;
        this.subsubcategory = q.subsubcategory;
    }

    public String changeId() {
        this.id = DataToolkit.getUniqueId();
        return this.id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
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

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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
