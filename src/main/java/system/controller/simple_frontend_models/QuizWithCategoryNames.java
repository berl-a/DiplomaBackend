package system.controller.simple_frontend_models;

import system.model.classes.quizzes.Quiz;

public class QuizWithCategoryNames extends Quiz {

    private String categoryName, subcategoryName, subsubcategoryName;

    QuizWithCategoryNames(Quiz quiz) {
        super(quiz);
    }

    public QuizWithCategoryNames(Quiz quiz, String categoryName, String subcategoryName, String subsubcategoryName) {
        super(quiz);
        this.categoryName = categoryName;
        this.subcategoryName = subcategoryName;
        this.subsubcategoryName = subsubcategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public String getSubsubcategoryName() {
        return subsubcategoryName;
    }

    public void setSubsubcategoryName(String subsubcategoryName) {
        this.subsubcategoryName = subsubcategoryName;
    }
}
