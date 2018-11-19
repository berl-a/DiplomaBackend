package system.model.games;

public class SingleChoiceAnswer implements Answer {

    private int answerIndex;
    private Double correct;

    public SingleChoiceAnswer(int answerIndex) {
        this.answerIndex = answerIndex;
    }

    public SingleChoiceAnswer(int answerIndex, Double correct) {
        this.answerIndex = answerIndex;
        this.correct = correct;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }

    public Double getCorrect() {
        return correct;
    }

    public void setCorrect(Double correct) {
        this.correct = correct;
    }
}
