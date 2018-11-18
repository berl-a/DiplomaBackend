package system.model.games;

public class SingleChoiceAnswer implements Answer {

    private int answerIndex;
    private boolean correct;

    public SingleChoiceAnswer(int answerIndex) {
        this.answerIndex = answerIndex;
    }

    public SingleChoiceAnswer(int answerIndex, boolean correct) {
        this.answerIndex = answerIndex;
        this.correct = correct;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
