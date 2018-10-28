package system.dao;

import org.springframework.stereotype.Repository;
import system.database.DatabaseToolkit;
import system.database.IntStringBlobDatabaseEntry;
import system.model.questions.*;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class QuestionGroupDao {

    public LinkedList<QuestionGroup> getQuestionGroups() {

        LinkedList<QuestionGroup> resultList = new LinkedList<>();

        Set<String> fields = new TreeSet<>();
        fields.add("QuestionGroupId_INT");
        fields.add("QuestionGroupContent_BLOB");

        LinkedList<IntStringBlobDatabaseEntry> databaseResult = DatabaseToolkit.getDataFromDatabase("QuestionGroups", fields);
        for(IntStringBlobDatabaseEntry entry : databaseResult) {
            Object questionGroupContent = entry.getField("QuestionGroupContent_BLOB");
            QuestionGroup group = (QuestionGroup) questionGroupContent;
            resultList.add(group);
        }
        return resultList;
    }
}
