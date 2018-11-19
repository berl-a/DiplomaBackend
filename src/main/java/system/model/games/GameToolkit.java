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
    private QuizService quizService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionGroupService questionGroupService;

//    public Game createGame(String quizId) {
//        Game game = new Game();
//        game.setQuiz(quizId);
//
//        Quiz quiz = quizService.getWithQuiz(quizId);
//        game.setNumber(generateListOfQuestions(quiz).getPoints().size());
//        game.setFullTime(quiz.getTime());
//        return game;
//    }

//    public ListOfQuestions generateListOfQuestions(Quiz quiz) {
//        LinkedList<String> questions = new LinkedList<>();
//        LinkedList<QuizPart> quizParts =  quiz.getParts();
//        for(QuizPart part : quizParts) {
//            LinkedList<String> questionsInGroup = questionGroupService.getQuestionsInGroup(part.getCategory());
//            if(part.getNumber() <= questionsInGroup.size()) {
//                questions.addAll(questionsInGroup);
//            } else {
//                LinkedList<String> questionsInGroupCopy = (LinkedList<String>) questionsInGroup.clone();
//                Collections.shuffle(questionsInGroupCopy);
//                questions.addAll(questionsInGroupCopy.subList(0, part.getNumber()));
//            }
//        }
//        return new ListOfQuestions(questions);
//    }
}
