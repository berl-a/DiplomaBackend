package system.dao;

import org.springframework.stereotype.Repository;
import system.database.DatabaseToolkit;
import system.database.IntStringBlobDatabaseEntry;
import system.model.questions.*;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class QuestionDao {

    public LinkedList<IQuestion> getQuestions() {

        LinkedList<IQuestion> resultList = new LinkedList<>();

        Set<String> fields = new TreeSet<>();
        fields.add("QuestionId_INT");
        fields.add("QuestionType_STRING");
        fields.add("QuestionContent_BLOB");

        LinkedList<IntStringBlobDatabaseEntry> databaseResult = DatabaseToolkit.getDataFromDatabase("Questions", fields);
        for(IntStringBlobDatabaseEntry entry : databaseResult) {
            QuestionType type = QuestionType.valueOf((String)entry.getField("QuestionType_STRING"));
            Object questionContent = entry.getField("QuestionContent_BLOB");
            IQuestion convertedQuestion = null;
            if(type == QuestionType.SINGLE_CHOICE)
                convertedQuestion = (SingleChoiceQuestion) questionContent;
            else if(type == QuestionType.MULTIPLE_CHOICE)
                convertedQuestion = (MultipleChoiceQuestion) questionContent;
            else if(type == QuestionType.FREE_TEXT)
                convertedQuestion = (FreeTextQuestion) questionContent;
            resultList.add(convertedQuestion);
        }
        return resultList;
    }
}
