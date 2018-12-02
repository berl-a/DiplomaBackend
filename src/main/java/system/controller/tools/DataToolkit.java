package system.controller.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.LinkedList;
import java.util.UUID;

public class DataToolkit {

    public static String getUniqueId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static JsonObject convertToJsonObject(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        JsonParser jsonParser = new JsonParser();
        try {
            return (JsonObject)jsonParser.parse(new JSONObject(mapper.writeValueAsString(o)).toString());
        } catch (IOException e) {
            e.printStackTrace();
            return new JsonObject();
        }
    }

    public static JsonArray convertToJsonArray(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        JsonParser jsonParser = new JsonParser();
        try {
            return (JsonArray)jsonParser.parse(new JSONArray(mapper.writeValueAsString(o)).toString());
        } catch (IOException e) {
            e.printStackTrace();
            return new JsonArray();
        }
    }

    public static byte[] objectToByteArray(Object o) {
        ByteArrayOutputStream baos;
        ObjectOutputStream out;
        baos = new ByteArrayOutputStream();
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(o);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return baos.toByteArray();
    }

    public static Object byteArrayToObject(byte[] b) {
        Object result = null;
        ByteArrayInputStream bais;
        ObjectInputStream in;
        try {
            bais = new ByteArrayInputStream(b);
            in = new ObjectInputStream(bais);
            result = in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static boolean areListsTheSame(LinkedList<String> list0, LinkedList<String> list1) {
        return list0.size() == list1.size() && list0.stream().allMatch(list1::contains) && list1.stream().allMatch(list0::contains);
    }

}
