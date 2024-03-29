package system.controller.service.database;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public interface DatabaseEntry<V> extends Serializable {
    /**
     * @return List of names of fields
     */
    Set<String> getFieldNames();

    /**
     * Get values of all the fields
     * @return map of field names and their values
     */
    HashMap<String, V> getFields();

    /**
     *
     * @param fieldName name of field, value of which you want to getWithQuiz
     * @return value of field with specified name
     */
    V getField(String fieldName);
}
