package com.json_news_item;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.PrintWriter;

public class JSONContainer {

    private static JSONArray jsonNewsList = new JSONArray();

    public static void addNewJsonRecord(JSONObject rec) {
        jsonNewsList.add(rec);
    }

    public JSONArray getJsonNewsList() {
        return jsonNewsList;
    }

    public static void displayJsonObjects(PrintWriter out) {


        out.print(jsonNewsList.toJSONString());
    }

    public static String encodeHtmlTag(String tag) {
        int length = tag.length();
        StringBuffer encodedTag = new StringBuffer(2 * length);
        for (int i = 0; i < length; i++) {
            char c = tag.charAt(i);
            if (c == '<') {
                encodedTag.append("&lt;");
            } else if (c == '>') {
                encodedTag.append("&gt;");
            } else if (c == '&') {
                encodedTag.append("&amp;");
            } else encodedTag.append(c);
        }
        return encodedTag.toString();
    }
}
