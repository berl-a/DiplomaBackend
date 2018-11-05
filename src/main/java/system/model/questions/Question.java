package system.model.questions;

import system.controller.tools.DataToolkit;
import system.dao.Idable;
import system.model.questions.QuestionType;

import javax.sql.rowset.serial.SerialBlob;
import java.io.Serializable;
import java.util.LinkedList;

public class Question implements Serializable, Idable {

    private String id;
    private QuestionType questionType;
    private String questionText, questionImage;
    private LinkedList<String> answerTexts;
    private LinkedList<String> answerImages;
    private LinkedList<Boolean> correctAnswers;

    Question() {
        this.id = DataToolkit.getUniqueId();
    }

    Question(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
    }

    public LinkedList<String> getAnswerTexts() {
        return answerTexts;
    }

    public void setAnswerTexts(LinkedList<String> answerTexts) {
        this.answerTexts = answerTexts;
    }

    public LinkedList<String> getAnswerImages() {
        return answerImages;
    }

    public void setAnswerImages(LinkedList<String> answerImages) {
        this.answerImages = answerImages;
    }

    public LinkedList<Boolean> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(LinkedList<Boolean> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
