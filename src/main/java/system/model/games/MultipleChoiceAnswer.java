package system.model.games;

import java.util.LinkedList;

public class MultipleChoiceAnswer implements Answer {

    private LinkedList<Integer> answerIndexes;
    private LinkedList<Boolean> answersCorrect;

    public MultipleChoiceAnswer(LinkedList<Integer> answerIndexes) {
        this.answerIndexes = answerIndexes;
    }

    public MultipleChoiceAnswer(LinkedList<Integer> answerIndexes, LinkedList<Boolean> answersCorrect) {
        this.answerIndexes = answerIndexes;
        this.answersCorrect = answersCorrect;
    }

    public LinkedList<Integer> getAnswerIndexes() {
        return answerIndexes;
    }

    public void setAnswerIndexes(LinkedList<Integer> answerIndexes) {
        this.answerIndexes = answerIndexes;
    }

    public LinkedList<Boolean> getAnswersCorrect() {
        return answersCorrect;
    }

    public void setAnswersCorrect(LinkedList<Boolean> answersCorrect) {
        this.answersCorrect = answersCorrect;
    }
}
