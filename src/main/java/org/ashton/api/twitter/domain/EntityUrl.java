package org.ashton.api.twitter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.StringJoiner;

public class EntityUrl {
  private int start;
  private int end;
  private String url;

  @JsonProperty("expanded_url")
  private String expandedUrl;

  @JsonProperty("display_url")
  private String displayUrl;

  public int getStart() {
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getEnd() {
    return end;
  }

  public void setEnd(int end) {
    this.end = end;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getExpandedUrl() {
    return expandedUrl;
  }

  public void setExpandedUrl(String expandedUrl) {
    this.expandedUrl = expandedUrl;
  }

  public String getDisplayUrl() {
    return displayUrl;
  }

  public void setDisplayUrl(String displayUrl) {
    this.displayUrl = displayUrl;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", EntityUrl.class.getSimpleName() + "[", "]")
        .add("start=" + start)
        .add("end=" + end)
        .add("url='" + url + "'")
        .add("expandedUrl='" + expandedUrl + "'")
        .add("displayUrl='" + displayUrl + "'")
        .toString();
  }
}
