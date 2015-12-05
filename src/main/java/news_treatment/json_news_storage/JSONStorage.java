package news_treatment.json_news_storage;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.PrintWriter;

public class JSONStorage {

    public JSONArray getJsonNewsStorageList() {
        return jsonNewsStorageList;
    }

    private JSONArray jsonNewsStorageList = new JSONArray();

    public void addNewJsonRecord(JSONObject rec) {
        jsonNewsStorageList.add(rec);
    }

//    public void displayContent(PrintWriter out, int newsCountForDisplay) {
//
//        if (newsCountForDisplay == 0) {
//            out.print(jsonNewsStorageList.toString());
//        } else {
//            newsPicks(out, newsCountForDisplay);
//        }
//    }

//    private void newsPicks(PrintWriter out, int newsCountForDisplay) {
//        JSONArray storageForDisplay = new JSONArray();
//        for (int i = 0; i < newsCountForDisplay; i++) {
//            storageForDisplay.add(jsonNewsStorageList.get(i));
//        }
//        out.print(storageForDisplay.toString());
//    }

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
