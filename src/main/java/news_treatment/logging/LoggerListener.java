package news_treatment.logging;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Aspect
public class LoggerListener {


    @Autowired
    private EventLogger eventLogger;

    @After("execution(* news_treatment.downloading_news_file.DownloadNewsFeedFile.download(..))")
    public void downloadLogEvent(JoinPoint joinPoint) {
        eventLogger.logEvent("Download of news file is finished!");
    }

    @After("execution(* news_treatment.xml_parser.XMLParserToJson.parseToJson(..))")
    public void xmlParserLogEvent(JoinPoint joinPoint) {
        eventLogger.logEvent("Xml news file has parsed!");
    }
}
