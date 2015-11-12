package com.news_config;


import com.json_news_item.JSONStorage;
import com.news_filters.FilteringByAuthor;
import com.xml_parser.XMLParserToJson;

public class NewsConfig {

    private JSONStorage jsonStorage;
    private XMLParserToJson xmlParserToJson;
    private FilteringByAuthor filteringByAuthor;

    public void setJsonStorage(JSONStorage jsonStorage) {
        this.jsonStorage = jsonStorage;
    }

    public void setXmlParserToJson(XMLParserToJson xmlParserToJson) {
        this.xmlParserToJson = xmlParserToJson;
    }

    public void setFilteringByAuthor(FilteringByAuthor filteringByAuthor) {
        this.filteringByAuthor = filteringByAuthor;
    }


    public JSONStorage getJsonStorage() {
        return jsonStorage;
    }

    public XMLParserToJson getXmlParserToJson() {
        return xmlParserToJson;
    }

    public FilteringByAuthor getFilteringByAuthor() {
        return filteringByAuthor;
    }
}
