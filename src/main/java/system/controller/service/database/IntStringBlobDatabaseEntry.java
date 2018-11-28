package system.controller.service.database;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class IntStringBlobDatabaseEntry implements DatabaseEntry<Object> {


    private HashMap<String, Object> content;

    public IntStringBlobDatabaseEntry(HashSet<String> fieldNames) {
        for(String s : fieldNames)
            content.put(s, null);
    }

    public IntStringBlobDatabaseEntry(HashMap<String, Object> content) {
        this.content = content;
    }

    @Override
    public Set<String> getFieldNames() {
        return content.keySet();
    }

    @Override
    public HashMap<String, Object> getFields() {
        return content;
    }

    @Override
    public Object getField(String fieldName) {
        return content.get(fieldName);
    }
}
