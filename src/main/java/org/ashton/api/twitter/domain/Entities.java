package org.ashton.api.twitter.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Entities {

  private List<EntityUrl> urls =  new ArrayList<>();

  public List<EntityUrl> getUrls() {
    return urls;
  }

  public void setUrls(List<EntityUrl> urls) {
    this.urls = urls;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Entities.class.getSimpleName() + "[", "]")
        .add("urls=" + urls)
        .toString();
  }
}
