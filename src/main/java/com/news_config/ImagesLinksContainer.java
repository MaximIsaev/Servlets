package com.news_config;

import java.util.ArrayList;

public class ImagesLinksContainer {

    private static ArrayList<String> imgListLink = new ArrayList<String>();

    public void setImgList(String link) {

        imgListLink.add(link);
    }

    public ArrayList getImgList() {
        return imgListLink;
    }

}
