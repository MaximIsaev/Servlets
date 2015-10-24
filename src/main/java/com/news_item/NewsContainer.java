package com.news_item;


import java.util.ArrayList;

public class NewsContainer {
    private static ArrayList<News> newsList = new ArrayList<News>();

    public static void setNewsList(News record) {
        newsList.add(record);
    }

    public static ArrayList<News> getNewsList() {
        return newsList;
    }


}
