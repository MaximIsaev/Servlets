package controller;

import news_treatment.jsp_handler.JspView;
import news_treatment.news_session.NewsSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class JspController {

    @Autowired
    private JspView jspView;
    @Autowired
    private NewsSession session;

    @RequestMapping(value = "/folder", method = RequestMethod.GET)
    public ModelAndView sendJsp(HttpServletRequest request) throws IOException {

        ModelAndView model = new ModelAndView("folder");
        List<String> content;
        String greeting = "Welcome!";
        if (session.isFirstTime(request)) {
            model.addObject("greeting", greeting);
        }

        String folder_name = request.getParameter("Folder_name");
        String existExpression = jspView.checkFields(folder_name);
        model.addObject("existExpression", existExpression);
        String imgFolderHomePath = jspView.getImgFolderHomePath();
        model.addObject("imgFolderHomePath", imgFolderHomePath);
        content = jspView.getImgFolderContent();
        model.addObject("content", content);

        return model;
    }

}
