package controller;

import configuration.NewsProcessing;
import org.json.simple.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewsController {

    ApplicationContext context = new ClassPathXmlApplicationContext("");

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONArray getJsonNews() {

        NewsProcessing newsProcessing = (NewsProcessing) context.getBean("newsProcessing");
        return newsProcessing.getNews();
    }

    @RequestMapping(value = "/*.jsp", method = RequestMethod.GET)
    public String chooseImgFolderPage() {
        return "SimplePage";
    }
}
