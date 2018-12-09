package system.model.classes.games;

import java.io.Serializable;
import java.util.HashMap;

public class PlayerAnswers implements Serializable {

    private HashMap<String, Answer> answers = new HashMap<>();

    public void addAnswer(String questionId, Answer answer) {
        answers.put(questionId, answer);
    }

    public HashMap<String, Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<String, Answer> answers) {
        this.answers = answers;
    }
}
