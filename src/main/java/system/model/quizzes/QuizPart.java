package system.model.quizzes;

import java.io.Serializable;

public class QuizPart implements Serializable{

    public QuizPart(String category, int numberOfQuestions) {
        this.category = category;
        this.numberOfQuestions = numberOfQuestions;
    }

    private String category;
    private int numberOfQuestions;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}
