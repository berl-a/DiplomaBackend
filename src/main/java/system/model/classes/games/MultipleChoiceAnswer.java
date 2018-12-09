package system.model.classes.games;

import java.util.LinkedList;

public class MultipleChoiceAnswer implements Answer {

    private LinkedList<Integer> answerIndexes;
    private Double correct;

    public MultipleChoiceAnswer(LinkedList<Integer> answerIndexes) {
        this.answerIndexes = answerIndexes;
    }

    public MultipleChoiceAnswer(LinkedList<Integer> answerIndexes, Double correct) {
        this.answerIndexes = answerIndexes;
        this.correct = correct;
    }

    public LinkedList<Integer> getAnswerIndexes() {
        return answerIndexes;
    }

    public void setAnswerIndexes(LinkedList<Integer> answerIndexes) {
        this.answerIndexes = answerIndexes;
    }

    @Override
    public Double getCorrect() {
        return correct;
    }

    public void setCorrect(Double correct) {
        this.correct = correct;
    }
}
