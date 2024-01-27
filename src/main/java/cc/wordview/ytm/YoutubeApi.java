package cc.wordview.ytm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import cc.wordview.ytm.network.Client;
import cc.wordview.ytm.response.SearchResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YoutubeApi {
    private static final Logger logger = LogManager.getLogger(YoutubeApi.class);

    public List<SearchResult> search(String query, int maxResults, String apiKey) {
        try {
            String url = Client.buildUrl(query, maxResults, apiKey);
            String response = Client.sendHttpRequest(url);

            JSONArray array = new JSONObject(response).getJSONArray("items");

            List<SearchResult> results = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {
                String entry = array.getJSONObject(i).toString();

                Gson gson = new Gson();
                SearchResult result = gson.fromJson(entry, SearchResult.class);

                results.add(result);
            }

            return results;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
