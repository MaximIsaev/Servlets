package configuration;


import news_treatment.downloading_news_file.DownloadNewsFeedFile;
import news_treatment.json_news_storage.JSONStorage;
import news_treatment.xml_parser.XMLParserToJson;
import configuration.sources.SourceConfig;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NewsProcessing {

    @Autowired
    SourceConfig sourceConfig;
    @Autowired
    DownloadNewsFeedFile downloadNewsFeedFile;
    @Autowired
    XMLParserToJson xmlParserToJson;


    public JSONArray getNews() {
        String downloadedNewsFilePath = downloadNewsFeedFile.download();
        return xmlParserToJson.parseToJson(downloadedNewsFilePath);
    }


}
