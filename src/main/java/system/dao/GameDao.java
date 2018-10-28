package system.dao;

import org.springframework.stereotype.Repository;
import system.database.DatabaseToolkit;
import system.database.IntStringBlobDatabaseEntry;
import system.model.games.Game;
import system.model.questions.QuestionGroup;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class GameDao {

    public LinkedList<Game> getGames() {

        LinkedList<Game> resultList = new LinkedList<>();

        Set<String> fields = new TreeSet<>();
        fields.add("GameId_INT");
        fields.add("GameContent_BLOB");

        LinkedList<IntStringBlobDatabaseEntry> databaseResult = DatabaseToolkit.getDataFromDatabase("Games", fields);
        for(IntStringBlobDatabaseEntry entry : databaseResult) {
            Object content = entry.getField("GameContent_BLOB");
            Game convertedObject = (Game) content;
            resultList.add(convertedObject);
        }
        return resultList;
    }


}
