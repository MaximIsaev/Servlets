package news_treatment.interceptors;

import org.json.simple.JSONArray;

public interface Filter {
    JSONArray doFilter(JSONArray jsonArray);
}
