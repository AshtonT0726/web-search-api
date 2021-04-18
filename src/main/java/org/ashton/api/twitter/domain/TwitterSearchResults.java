package org.ashton.api.twitter.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TwitterSearchResults {

  public List<TwitterSearchResult> getData() {
    return data;
  }

  public void setData(List<TwitterSearchResult> data) {
    this.data = data;
  }

  private List<TwitterSearchResult> data = new ArrayList<>();

  @Override
  public String toString() {
    return new StringJoiner(", ", TwitterSearchResults.class.getSimpleName() + "[", "]")
        .add("data=" + data)
        .toString();
  }
}
