package system.controller.simple_frontend_models;

import system.model.questions.QuestionGroup;

public class QuestionGroupWithNumberOfQuestions extends QuestionGroup {

    private int numberOfQuestions;

    public QuestionGroupWithNumberOfQuestions(QuestionGroup questionGroup, int numberOfQuestions) {
        super(questionGroup);
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}
