package configuration;


import news_treatment.downloading_news_file.DownloadNewsFeedFile;
import news_treatment.json_news_storage.JSONStorage;
import news_treatment.xml_parser.XMLParserToJson;
import configuration.sources.SourceConfig;
import org.json.simple.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NewsProcessing {

    SourceConfig sourceConfig;
    DownloadNewsFeedFile downloadNewsFeedFile;
    XMLParserToJson xmlParserToJson;
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

    public NewsProcessing() {
        sourceConfig = (SourceConfig) context.getBean("sourceConfig");
        downloadNewsFeedFile = (DownloadNewsFeedFile) context.getBean("downloadNewsFeedFile");
        xmlParserToJson = (XMLParserToJson) context.getBean("xmlParser");
    }


    public JSONArray getNews() {
        String downloadedNewsFilePath = downloadNewsFeedFile.download();
        return xmlParserToJson.parseToJson(downloadedNewsFilePath);
    }


}
