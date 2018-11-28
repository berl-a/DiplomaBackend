package system.controller.service.database;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class StringDatabaseEntry implements DatabaseEntry<String> {

    private HashMap<String, String> content;

    StringDatabaseEntry(HashSet<String> fieldNames) {
        for(String s : fieldNames)
            content.put(s, null);
    }

    public StringDatabaseEntry(HashMap<String, String> content) {
        this.content = content;
    }

    @Override
    public Set<String> getFieldNames() {
        return content.keySet();
    }

    @Override
    public HashMap<String, String> getFields() {
        return content;
    }

    @Override
    public String getField(String fieldName) {
        return content.get(fieldName);
    }

    @Override
    public String toString() {
        return "StringDatabaseEntry{" +
                "content=" + content +
                '}';
    }
}
