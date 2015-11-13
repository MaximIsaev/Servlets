package com.news_filters;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FilteringByAuthor {

    private String authorName = "";

    public FilteringByAuthor(String authorName) {
        this.authorName = authorName;
    }


    public void filter(JSONArray jsonArray, PrintWriter writer) throws UnsupportedEncodingException {

        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            displayThroughFilter(jsonObject, writer);

        }
    }

    private void displayThroughFilter(JSONObject jsonObject, PrintWriter writer) {

        if (jsonObject.get("author").equals(authorName) || authorName.equals("")) {
            writer.write(jsonObject.toString());
        }
    }
}
