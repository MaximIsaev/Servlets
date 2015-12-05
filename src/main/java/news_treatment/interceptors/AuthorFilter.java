package news_treatment.interceptors;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AuthorFilter implements Filter {

    private String authorName;
    private JSONArray newsForDisplay = new JSONArray();

    public AuthorFilter(String authorName) {
        this.authorName = authorName;
    }

    public JSONArray doFilter(JSONArray jsonArray) {

        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            checkAuthor(jsonObject);
        }
        return newsForDisplay;
    }

    private void checkAuthor(JSONObject jsonObject) {

        if (jsonObject.get("author").equals(authorName) || authorName.equals("")) {
            newsForDisplay.add(jsonObject);
        }
    }
}
