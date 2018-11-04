package system.model.questions;

public class FreeTextQuestion implements IQuestion {

    private String id;
    private String text, image;
    private double maxPoints;

    private FreeTextQuestionAnswer correctAnswer;

    @Override
    public String getId() {
        return id;
    }

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

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.FREE_TEXT;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getMaxPoints() {
        return maxPoints;
    }

    public FreeTextQuestionAnswer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(FreeTextQuestionAnswer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
