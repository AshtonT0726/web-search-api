package org.ashton.api.twitter.search;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.ashton.api.twitter.domain.TwitterSearchResult;
import org.ashton.api.twitter.domain.TwitterSearchResults;
import org.ashton.mood.MoodEnum;
import org.springframework.stereotype.Service;

/**
 * Twitter Recent search.
 *
 * <p>https://developer.twitter.com/en/docs/twitter-api/tweets/search/quick-start/recent-search
 */
@Service
public class RecentSearch {

  private static final String RECENT_SEARCH_API = "https://api.twitter.com/2/tweets/search/recent";

  public TwitterSearchResults search(String searchString) throws IOException, URISyntaxException {

    String searchResponse = null;

    HttpClient httpClient =
        HttpClients.custom()
            .setDefaultRequestConfig(
                RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
            .build();

    URIBuilder uriBuilder = new URIBuilder(RECENT_SEARCH_API);
    ArrayList<NameValuePair> queryParameters;
    queryParameters = new ArrayList<>();
    queryParameters.add(new BasicNameValuePair("query", searchString + " lang:en -is:retweet"));
    queryParameters.add(new BasicNameValuePair("max_results", "100"));
    queryParameters.add(
        new BasicNameValuePair(
            "tweet.fields",
            "public_metrics,author_id,created_at,entities,geo,in_reply_to_user_id,lang,possibly_sensitive,referenced_tweets,source"));
    uriBuilder.addParameters(queryParameters);

    HttpGet httpGet = new HttpGet(uriBuilder.build());
    httpGet.setHeader("Authorization", String.format("Bearer %s", getBearerToken()));
    httpGet.setHeader("Content-Type", "application/json");

    HttpResponse response = httpClient.execute(httpGet);
    HttpEntity entity = response.getEntity();
    if (null != entity) {
      searchResponse = EntityUtils.toString(entity, "UTF-8");
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return mapper.readValue(searchResponse, TwitterSearchResults.class);
    }

    return null;
  }

  public List<TwitterSearchResult> filerAndOrder(List<TwitterSearchResult> rawResults) {
    List<TwitterSearchResult> filtered = new ArrayList<>();
    for (TwitterSearchResult result : rawResults)
      if (result.getPublicMetrics().get("like_count") > 5) {
        filtered.add(result);
      }
    Collections.sort(filtered);
    return filtered;
  }

  private String getBearerToken() {
    String bearerToken = System.getenv("BEARER_TOKEN");
    System.out.println("bearerToken=" + bearerToken);
    return bearerToken;
  }

  // To set your enviornment variables in your terminal run the following line:
  // export 'BEARER_TOKEN'='<your_bearer_token>'

  public static void main(String args[]) throws IOException, URISyntaxException {

    // Replace the search term with a term of your choice
    RecentSearch search = new RecentSearch();
    TwitterSearchResults response = search.search(MoodEnum.DEPRESSION.toString().toLowerCase());
    List<TwitterSearchResult> sorted = search.filerAndOrder(response.getData());
  }
}
