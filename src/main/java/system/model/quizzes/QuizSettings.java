package system.model.quizzes;

import java.util.HashMap;

public class QuizSettings {

    private HashMap<QuizSetting, Object> quizSettings = new HashMap<>();

    public QuizSettings() {
    }

    public QuizSettings(HashMap<QuizSetting, Object> quizSettings) {
        this.quizSettings = quizSettings;
    }

    public HashMap<QuizSetting, Object> getQuizSettings() {
        return quizSettings;
    }

    public void setQuizSettings(HashMap<QuizSetting, Object> quizSettings) {
        this.quizSettings = quizSettings;
    }
}
