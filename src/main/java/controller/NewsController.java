package controller;

import configuration.NewsProcessing;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Controller
public class NewsController {


    @Autowired
    NewsProcessing newsProcessing;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getJsonNews(Model model) throws ServletException, IOException, ParserConfigurationException, SAXException {

        model.addAttribute("news", newsProcessing.getNews());
        return "jsonTemplate";
    }
}
