package system.model.games;

import java.io.Serializable;
import java.util.HashMap;

public class PlayerAnswers implements Serializable {

    private HashMap<String, Integer> answers = new HashMap<>();

    public void addAnswer(String questionId, Integer answerIndex) {
        answers.put(questionId, answerIndex);
    }

    public HashMap<String, Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<String, Integer> answers) {
        this.answers = answers;
    }
}
