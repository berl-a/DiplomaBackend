package system.model.quizzes;

import system.model.questions.QuestionCategory;

import java.io.Serializable;

public class QuizPart implements Serializable{

    public QuizPart(QuestionCategory category, int numberOfQuestionsFromCategory) {
        this.category = category;
        this.numberOfQuestionsFromCategory = numberOfQuestionsFromCategory;
    }

    private QuestionCategory category;
    private int numberOfQuestionsFromCategory;

    public QuestionCategory getCategory() {
        return category;
    }

    public void setCategory(QuestionCategory category) {
        this.category = category;
    }

    public int getNumberOfQuestionsFromCategory() {
        return numberOfQuestionsFromCategory;
    }

    public void setNumberOfQuestionsFromCategory(int numberOfQuestionsFromCategory) {
        this.numberOfQuestionsFromCategory = numberOfQuestionsFromCategory;
    }
}
