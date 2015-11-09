package com.json_storage;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.PrintWriter;

public class JSONStorage {

    private JSONArray jsonNewsStorage = new JSONArray();

    public void addNewJsonRecord(JSONObject rec) {
        jsonNewsStorage.add(rec);
    }

    public JSONArray getJsonNewsStorage() {
        return jsonNewsStorage;
    }

    public void displayContent(PrintWriter out) {
        out.print(jsonNewsStorage.toJSONString());
    }

    //Using for easy viewing
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
