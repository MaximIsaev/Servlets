package com.json_news_item;


import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.ArrayList;

public class JSONContainer {
    private static ArrayList<JSONObject> jsonNewsList = new ArrayList<JSONObject>();

    public static void addNewJsonRecord(JSONObject rec) {
        jsonNewsList.add(rec);
    }

    public static ArrayList<JSONObject> getJsonNewsList() {
        return jsonNewsList;
    }

    public static void displayJsonObjects(PrintWriter out) {
        for (int i = 0; i < jsonNewsList.size(); i++) {
            out.println(jsonNewsList.get(i));
        }
        out.close();

    }
}
