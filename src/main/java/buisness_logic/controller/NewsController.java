package buisness_logic.controller;


import buisness_logic.xml_parser.XMLParser;
import org.json.simple.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewsController {

    XMLParser xmlParser;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONArray getJsonNews() {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        xmlParser = (XMLParser) context.getBean("xmlParser");

        xmlParser.parse();

        return xmlParser.getJsonStorage().getJsonNewsStorage();
    }

    @RequestMapping(value = "/*.jsp", method = RequestMethod.GET)
    public String chooseImgFolderPage() {
        return "SimplePage";
    }

}
