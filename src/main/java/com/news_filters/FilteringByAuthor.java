package com.news_filters;


import com.json_news_item.JSONContainer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.UnsupportedEncodingException;

import java.io.PrintWriter;

public class FilteringByAuthor {

    static final String AUTHOR_NAME = "";

    public static void filter(JSONArray jsonArray, PrintWriter writer) throws UnsupportedEncodingException {
        JSONObject jsonObject;

        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            if (jsonObject.get("author").equals(AUTHOR_NAME) || AUTHOR_NAME.equals("")) {
                writer.write(JSONContainer.encodeHtmlTag(jsonObject.toString()) + "<br><br>");
            }
        }
    }
}
