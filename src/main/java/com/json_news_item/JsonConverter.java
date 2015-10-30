package com.json_news_item;

import com.news_item.News;
import org.json.simple.JSONObject;


public class JsonConverter {
    public static void addJsonInContainer(JSONObject rec, News nrec) {

        rec.put("ID", nrec.getId());
        rec.put("Title", nrec.getTitle());
        rec.put("Description", nrec.getDescription());
        rec.put("Author", nrec.getAuthor());
        rec.put("PubDate", nrec.getPubdate());
        JSONContainer.addNewJsonRecord(rec);
    }
}
