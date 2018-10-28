package system.dao;

import org.springframework.stereotype.Repository;
import system.database.DatabaseToolkit;
import system.database.IntStringBlobDatabaseEntry;
import system.model.games.Game;
import system.model.quizzes.Quiz;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class QuizDao {

    public LinkedList<Quiz> getQuizzes() {

        LinkedList<Quiz> resultList = new LinkedList<>();

        Set<String> fields = new TreeSet<>();
        fields.add("QuizId_INT");
        fields.add("QuizContent_BLOB");

        LinkedList<IntStringBlobDatabaseEntry> databaseResult = DatabaseToolkit.getDataFromDatabase("Quizzes", fields);
        for(IntStringBlobDatabaseEntry entry : databaseResult) {
            Object content = entry.getField("QuizContent_BLOB");
            Quiz convertedObject = (Quiz) content;
            resultList.add(convertedObject);
        }
        return resultList;
    }


}
