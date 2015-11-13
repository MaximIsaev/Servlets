package configuration_interface;


import business_logic.get_news_feed_file.DownloadNewsFeedFile;
import business_logic.xml_parser.XMLParser;
import org.json.simple.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DisplayConfig {

    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

    private int newsDisplayCounter = 5;
    DownloadNewsFeedFile downloadNewsFeedFile = (DownloadNewsFeedFile) context.getBean("downloadNewsFeedFile");
    XMLParser xmlParser = (XMLParser) context.getBean("xmlParser");

    public DisplayConfig() {
        downloadNewsFeedFile.download();
        xmlParser.parse();
    }

    public JSONArray getNews() {
        return xmlParser.getJsonStorage().getNews();
    }


    public void setNewsDisplayCounter(int newsDisplayCounter) {
        this.newsDisplayCounter = newsDisplayCounter;
    }

    public int getNewsDisplayCounter() {
        return newsDisplayCounter;
    }

}
