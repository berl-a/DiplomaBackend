package system.model.games;

public class FreeTextAnswer implements Answer {

    private String answer;
    private boolean correct;

    public FreeTextAnswer(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public FreeTextAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
