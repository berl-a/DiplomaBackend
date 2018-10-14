package system.model;

public class FreeTextQuestion implements IQuestion {

    private String text, image;
    private double maxPoints;

    private FreeTextQuestionAnswer correctAnswer;

    @Override
    public void setMaxPoints(double maxPoints) {
        this.maxPoints = maxPoints;
    }

    @Override
    public double getPointsForAnswer(IAnswer answer) {
        return
                correctAnswer.getAnswers().getFirst().getText() != null &&
                        correctAnswer.getAnswers().getFirst().getText().equals(answer.getAnswers().getFirst().getText())
                        ?
                        maxPoints : 0;
    }
}
