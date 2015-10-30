package com.news_filters;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.PrintWriter;

public class FilteringByAuthor {

    static final String AUTHOR_NAME = "admin";

    public static void filter(JSONArray jsonArray, PrintWriter writer) {
        JSONObject jsonObject;

        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            if (jsonObject.get("author").equals(AUTHOR_NAME)) {
                writer.write(jsonObject.toString() + "<br>");
            }
        }
    }
}
