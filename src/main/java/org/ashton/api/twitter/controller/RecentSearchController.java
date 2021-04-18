package org.ashton.api.twitter.controller;

import java.util.List;
import org.ashton.api.twitter.domain.TwitterSearchResult;
import org.ashton.api.twitter.search.RecentSearch;
import org.ashton.mood.MoodEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/twitter")
public class RecentSearchController {

  @Autowired private RecentSearch recentSearch;

  @GetMapping("/search/recent/{keyword}")
  public ResponseEntity<List<TwitterSearchResult>> getOne(@PathVariable String keyword) {

    MoodEnum moodEnum = MoodEnum.fromValue(keyword);
    if (moodEnum == null)
      return ResponseEntity.badRequest().build();
    try {
      List<TwitterSearchResult> results = recentSearch.search(moodEnum.getSearchKey()).getData();
      results = recentSearch.filerAndOrder(results);
      return ResponseEntity.ok(results);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
