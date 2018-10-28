package system.model.questions;

import system.service.QuestionsToolkit;

import java.util.LinkedList;

public class MultipleChoiceQuestion implements IQuestion {

    public static final int NUMBER_OF_POINTS_IF_ANY_ANSWER_WAS_WRONG = 0;

    public MultipleChoiceQuestion(double maxPoints, String text, String image) {
        this.maxPoints = maxPoints;
        this.text = text;
        this.image = image;
    }

    private String id;
    private double maxPoints;
    private String text, image;

    private LinkedList<OneAnswer> answers = new LinkedList<>();
    private MultipleChoiceQuestionAnswer correctAnswer;

    @Override
    public void setMaxPoints(double maxPoints) {
        this.maxPoints = maxPoints;
    }

    @Override
    public double getPointsForAnswer(IAnswer userAnswer) {
        if(userAnswer.getAnswers().stream().allMatch(answer -> QuestionsToolkit.listContainsAnswer(correctAnswer.getAnswers(), answer))) {
            return userAnswer.getAnswers().size() / correctAnswer.getAnswers().size() * maxPoints;
        } else
            return NUMBER_OF_POINTS_IF_ANY_ANSWER_WAS_WRONG;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.MULTIPLE_CHOICE;
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

    public MultipleChoiceQuestionAnswer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(MultipleChoiceQuestionAnswer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMaxPoints() {
        return maxPoints;
    }
}
