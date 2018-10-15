package system.dao;

import org.springframework.stereotype.Repository;
import system.database.DatabaseToolkit;
import system.database.StringDatabaseEntry;
import system.model.IQuestion;
import system.model.QuestionType;
import system.model.SingleChoiceQuestion;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class QuestionsDao {

    public LinkedList<IQuestion> getQuestions() {

        LinkedList<IQuestion> resultList = new LinkedList<>();

        Set<String> fields = new TreeSet<>();
        fields.add("QuestionId_INT");
        fields.add("QuestionType");
        fields.add("QuestionContent_BLOB");

        LinkedList<StringDatabaseEntry> databaseResult = DatabaseToolkit.getStringDataFromDatabase("Hospitacja", fields);
        for(StringDatabaseEntry entry : databaseResult) {
            QuestionType type = QuestionType.valueOf(entry.getField("QuestionType"));
            entry.getField("QuestionContent_BLOB";
            IQuestion convertedQuestion = null;
            if(type == QuestionType.SINGLE_CHOICE)
                convertedQuestion = new SingleChoiceQuestion(questionAsJSON);
            resultList.add(

            );
        }
        return resultList;
    }
}
