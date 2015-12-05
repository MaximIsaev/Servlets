package news_treatment.interceptors;

import org.json.simple.JSONArray;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class NewsInterceptor extends HandlerInterceptorAdapter {

    Filter authorFilter;

    public NewsInterceptor(AuthorFilter authorFilter) {

        this.authorFilter = authorFilter;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        JSONArray jsonArray = (JSONArray) modelAndView.getModel().get("news");
        JSONArray filteredNews = new JSONArray();
        if (jsonArray != null) {
            filteredNews = authorFilter.doFilter(jsonArray);
        }
        modelAndView.getModel().put("news", filteredNews);


    }
}
