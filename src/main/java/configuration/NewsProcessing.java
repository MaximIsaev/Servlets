package configuration;


import com.get_news_feed_file.DownloadNewsFeedFile;
import com.json_news_item.JSONStorage;
import com.xml_parser.XMLParserToJson;
import configuration.sources.SourceConfig;

public class NewsProcessing {

    SourceConfig sourceConfig = new SourceConfig();
    DownloadNewsFeedFile downloadNewsFeedFile = new DownloadNewsFeedFile(sourceConfig);
    XMLParserToJson xmlParserToJson = new XMLParserToJson(sourceConfig);

    public JSONStorage getNews() {
        String downloadedNewsFilePath = downloadNewsFeedFile.download();
        return xmlParserToJson.parseToJson(downloadedNewsFilePath);
    }


}
