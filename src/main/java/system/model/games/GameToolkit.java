package system.model.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import system.model.quizzes.Quiz;
import system.model.quizzes.QuizPart;
import system.service.QuestionGroupService;
import system.service.QuestionService;
import system.service.QuizService;

import java.util.Collections;
import java.util.LinkedList;

@Component
public class GameToolkit {

    @Autowired
    private static QuizService quizService;
    @Autowired
    private static QuestionService questionService;
    @Autowired
    private static QuestionGroupService questionGroupService;

    public static Game createGame(String quizId) {
        Game game = new Game();
        game.setQuiz(quizId);

        Quiz quiz = quizService.get(quizId);
        game.setNumberOfQuestions(generateListOfQuestions(quiz).getQuestionIds().size());
        game.setFullTime(quiz.getTime());
        return game;
    }

    public static ListOfQuestions generateListOfQuestions(Quiz quiz) {
        LinkedList<String> questions = new LinkedList<>();
        LinkedList<QuizPart> quizParts =  quiz.getParts();
        for(QuizPart part : quizParts) {
            LinkedList<String> questionsInGroup = questionGroupService.getQuestionsInGroup(part.getCategory());
            if(part.getNumberOfQuestions() <= questionsInGroup.size()) {
                questions.addAll(questionsInGroup);
            } else {
                LinkedList<String> questionsInGroupCopy = (LinkedList<String>) questionsInGroup.clone();
                Collections.shuffle(questionsInGroupCopy);
                questions.addAll(questionsInGroupCopy.subList(0, part.getNumberOfQuestions()));
            }
        }
        return new ListOfQuestions(questions);
    }
}
