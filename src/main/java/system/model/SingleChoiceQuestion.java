package system.model;

import org.json.JSONObject;

import java.util.LinkedList;

public class SingleChoiceQuestion implements IQuestion {

    public SingleChoiceQuestion(double maxPoints, String text, String image) {
        this.maxPoints = maxPoints;
        this.text = text;
        this.image = image;
    }

    private double maxPoints;
    private String text;
    private String image;

    private LinkedList<OneAnswer> answers = new LinkedList<>();
    private SingleChoiceQuestionAnswer correctAnswer;

    @Override
    public void setMaxPoints(double maxPoints) {
        this.maxPoints = maxPoints;
    }

    @Override
    public double getPointsForAnswer(IAnswer answer) {
        boolean answerCorrect = true;
        if(correctAnswer.getAnswer().getText() != null && !correctAnswer.getAnswer().getText().equals(answer.getAnswers().getFirst().getText())) answerCorrect = false;
        if(correctAnswer.getAnswer().getImage() != null && !correctAnswer.getAnswer().getImage().equals(answer.getAnswers().getFirst().getImage())) answerCorrect = false;
        return answerCorrect ? maxPoints : 0;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.SINGLE_CHOICE;
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

    public LinkedList<OneAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(LinkedList<OneAnswer> answers) {
        this.answers = answers;
    }

    public SingleChoiceQuestionAnswer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(SingleChoiceQuestionAnswer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
