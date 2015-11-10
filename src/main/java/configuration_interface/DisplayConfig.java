package configuration_interface;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DisplayConfig {

    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

    private int newsCount;
    SourceConfig sourceConfig = (SourceConfig) context.getBean("sourceConfig");

    public void setNewsCount(int newsCount) {
        this.newsCount = newsCount;
    }


    public int getNewsCount() {
        return newsCount;
    }

}
