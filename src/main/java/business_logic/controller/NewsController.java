package business_logic.controller;


import configuration_interface.DisplayConfig;
import org.json.simple.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewsController {


    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONArray getJsonNews() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        DisplayConfig displayConfig = (DisplayConfig) context.getBean("displayConfig");
        return displayConfig.getNews();
    }

    @RequestMapping(value = "/*.jsp", method = RequestMethod.GET)
    public String chooseImgFolderPage() {
        return "SimplePage";
    }

}
