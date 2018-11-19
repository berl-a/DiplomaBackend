package system.model.games;

public class FreeTextAnswer implements Answer {

    private String answer;
    private Double correct;

    public FreeTextAnswer(String answer, Double correct) {
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

    public Double getCorrect() {
        return correct;
    }

    public void setCorrect(Double correct) {
        this.correct = correct;
    }
}
