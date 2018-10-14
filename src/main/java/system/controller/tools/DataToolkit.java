package system.controller.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataToolkit {

    public static LinkedList<String> getAllStringsInsideIdPTags(String text) {
        LinkedList<String> res = new LinkedList<>();
        Pattern MY_PATTERN = Pattern.compile("\\<p class='orderId text-hide'>(.*?)\\</p>");
        Matcher m = MY_PATTERN.matcher(text);
        while (m.find()) {
            String s = m.group(1);
            res.add(s);
//            System.out.println("SDFDSF IS " + s);
        }
        return res;
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

//    public static LinkedList<Quiz> listFromString(String listAsString) {
//        JSONArray array = new JSONArray(listAsString);
//        LinkedList<Quiz> quizzes = new LinkedList<>();
//        for(int i = 0; i < array.length(); i ++) {
//            quizzes.add(fromString((String)array.getJSONObject(i).toString()));
//        }
//        return quizzes;
//    }
//
//    public static Quiz fromString(String quizAsString) {
//        JSONObject quizAsJson = new JSONObject(quizAsString);
//        JSONArray questionsAsJson = quizAsJson.getJSONArray("questions");
//        LinkedList<IQuestion> questions = new LinkedList<>();
//        for(int i = 0; i < questionsAsJson.length(); i ++) {
//            JSONArray answersAsJson = questionsAsJson.getJSONObject(i).getJSONArray("answers");
//            LinkedList<IAnswer> answers = new LinkedList<>();
//            for(int a = 0; a < answersAsJson.length(); a ++) {
//                answers.add(new IAnswer(
//                        (long)answersAsJson.getJSONObject(a).getInt("id"),
//                        answersAsJson.getJSONObject(a).getString("text"),
//                        answersAsJson.getJSONObject(a).getBoolean("correct")
//                ));
//            }
//            questions.add(new IQuestion(
//                    (long)questionsAsJson.getJSONObject(i).getInt("id"),
//                    questionsAsJson.getJSONObject(i).getString("text"),
//                    answers
//            ));
//        }
//        return new Quiz(
//                quizAsJson.getInt("id"),
//                quizAsJson.getString("name"),
//                quizAsJson.getString("description"),
//                quizAsJson.getString("imageURL"),
//                quizAsJson.getBoolean("publicQuiz"),
//                questions
//        );
//    }
}
