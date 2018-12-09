package system.controller.simple_frontend_models;

import system.model.classes.quizzes.QuizPart;

public class QuizPartWithCategoryNames extends QuizPart {

    private String categoryName, subcategoryName, subsubcategoryName;

    QuizPartWithCategoryNames(QuizPart quiz) {
        super(quiz);
    }

    public QuizPartWithCategoryNames(QuizPart quizPart, String categoryName, String subcategoryName, String subsubcategoryName) {
        super(quizPart);
        this.categoryName = categoryName;
        this.subcategoryName = subcategoryName;
        this.subsubcategoryName = subsubcategoryName;
    }

    public QuizPartWithCategoryNames(String id, String name, String quiz, int order, int number, String category, String subcategory, String subsubcategory, String categoryName, String subcategoryName, String subsubcategoryName) {
        super(id, name, quiz, order, number, category, subcategory, subsubcategory);
        this.categoryName = categoryName;
        this.subcategoryName = subcategoryName;
        this.subsubcategoryName = subsubcategoryName;
    }

    public QuizPartWithCategoryNames(String name, String quiz, int order, int number, String category, String subcategory, String subsubcategory, String categoryName, String subcategoryName, String subsubcategoryName) {
        super(name, quiz, order, number, category, subcategory, subsubcategory);
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
