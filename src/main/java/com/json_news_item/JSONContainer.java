package com.json_news_item;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.PrintWriter;
import java.util.logging.Logger;

public class JSONContainer {
    private final static Logger jsonLog = Logger.getLogger(com.json_news_item.JSONContainer.class.getName());

    private static JSONArray jsonNewsList = new JSONArray();

    public static void addNewJsonRecord(JSONObject rec) {
        jsonNewsList.add(rec);
    }

    public static JSONArray getJsonNewsList() {
        return jsonNewsList;
    }

    public static void displayJsonObjects(PrintWriter out) {

        jsonLog.info("method displayJsonObject invoked successfully");

        out.print(jsonNewsList.toJSONString());
    }

    public static String encodeHtmlTag(String tag) {
        if (tag == null) {
            return null;
        }
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
