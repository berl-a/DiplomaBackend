package system.controller.simple_frontend_models;

import system.model.classes.questions.Question;

/**
 * Question with category, subcategory and subsubcategory names
 */
public class QuestionWithCategoryNames extends Question {

    private String categoryName, subcategoryName, subsubcategoryName;

    QuestionWithCategoryNames(Question question) {
        super(question);
    }

    public QuestionWithCategoryNames(Question question, String categoryName, String subcategoryName, String subsubcategoryName) {
        super(question);
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
