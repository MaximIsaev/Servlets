package com.news_config;


import java.util.ArrayList;

public class NewsLinks {

    private static ArrayList<String> newsListLink = new ArrayList<String>();

    public void setNewsList(String link) {

        newsListLink.add(link);
    }

    public ArrayList getNewsList() {
        return newsListLink;
    }
}
