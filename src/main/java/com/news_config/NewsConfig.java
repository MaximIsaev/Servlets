package com.news_config;


import com.json_storage.JSONStorage;
import com.news_filters.FilteringByAuthor;
import com.xml_parser.XMLParser;

public class NewsConfig {

    private JSONStorage jsonStorage;
    private XMLParser xmlParser;
    private FilteringByAuthor filteringByAuthor;

    public void setJsonStorage(JSONStorage jsonStorage) {
        this.jsonStorage = jsonStorage;
    }

    public void setXmlParser(XMLParser xmlParser) {
        this.xmlParser = xmlParser;
    }

    public void setFilteringByAuthor(FilteringByAuthor filteringByAuthor) {
        this.filteringByAuthor = filteringByAuthor;
    }


    public JSONStorage getJsonStorage() {
        return jsonStorage;
    }

    public XMLParser getXmlParser() {
        return xmlParser;
    }

    public FilteringByAuthor getFilteringByAuthor() {
        return filteringByAuthor;
    }
}
