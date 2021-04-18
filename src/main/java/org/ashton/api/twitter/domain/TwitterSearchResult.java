package org.ashton.api.twitter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.StringJoiner;

public class TwitterSearchResult implements Comparable<TwitterSearchResult>{

  private String id;
  private String source;
  @JsonProperty("author_id")
  private String authorId;
  private String lang;
  @JsonProperty("created_at")
  private String createdAt;
  @JsonProperty("possibly_sensitive")
  private boolean possiblySensitive;
  private String text;
  @JsonProperty("public_metrics")
  private Map<String, Integer> publicMetrics;
  private Entities entities;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getAuthorId() {
    return authorId;
  }

  public void setAuthorId(String authorId) {
    this.authorId = authorId;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public boolean isPossiblySensitive() {
    return possiblySensitive;
  }

  public void setPossiblySensitive(boolean possiblySensitive) {
    this.possiblySensitive = possiblySensitive;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Map<String, Integer> getPublicMetrics() {
    return publicMetrics;
  }

  public void setPublicMetrics(Map<String, Integer> publicMetrics) {
    this.publicMetrics = publicMetrics;
  }

  public Entities getEntities() {
    return entities;
  }

  public void setEntities(Entities entities) {
    this.entities = entities;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", TwitterSearchResult.class.getSimpleName() + "[", "]")
        .add("id='" + id + "'")
        .add("source='" + source + "'")
        .add("authorId='" + authorId + "'")
        .add("lang='" + lang + "'")
        .add("createdAt='" + createdAt + "'")
        .add("possiblySensitive=" + possiblySensitive)
        .add("text='" + text + "'")
        .add("publicMetrics=" + publicMetrics)
        .add("entities=" + entities)
        .toString();
  }

  @Override
  public int compareTo(TwitterSearchResult other) {
    return other.publicMetrics.get("like_count")- publicMetrics.get("like_count");
  }
}
