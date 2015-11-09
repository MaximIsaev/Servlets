package com.news_config;


import com.json_news_item.JSONContainer;
import com.news_filters.FilteringByAuthor;
import com.xml_parser.XMLParser;

public class NewsConfig {

    private JSONContainer jsonContainer;
    private XMLParser xmlParser;
    private FilteringByAuthor filteringByAuthor;

    public void setJsonContainer(JSONContainer jsonContainer) {
        this.jsonContainer = jsonContainer;
    }

    public void setXmlParser(XMLParser xmlParser) {
        this.xmlParser = xmlParser;
    }

    public void setFilteringByAuthor(FilteringByAuthor filteringByAuthor) {
        this.filteringByAuthor = filteringByAuthor;
    }


    public JSONContainer getJsonContainer() {
        return jsonContainer;
    }

    public XMLParser getXmlParser() {
        return xmlParser;
    }

    public FilteringByAuthor getFilteringByAuthor() {
        return filteringByAuthor;
    }
}
