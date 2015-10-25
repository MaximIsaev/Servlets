package com.json_news_item;


import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

public class JSONContainer {
    private final static Logger jsonLog = Logger.getLogger(com.json_news_item.JSONContainer.class.getName());

    private static ArrayList<JSONObject> jsonNewsList = new ArrayList<JSONObject>();

    public static void addNewJsonRecord(JSONObject rec) {
        jsonNewsList.add(rec);
    }

    public static ArrayList<JSONObject> getJsonNewsList() {
        return jsonNewsList;
    }

    public static void displayJsonObjects(PrintWriter out) {

        jsonLog.info("method displayJsonObject invoked successfully");

        for (int i = 0; i < jsonNewsList.size(); i++) {
            String buff = encodeHtmlTag(jsonNewsList.get(i).toString());
            out.println(buff + "<br>");
        }
        out.close();
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
                // } else if (c == '"') {
                //   encodedTag.append("&quot;");
                // } else if (c == ' ') {
                // encodedTag.append("&nbsp;");
            } else encodedTag.append(c);
        }
        return encodedTag.toString();
    }
}
