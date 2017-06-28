package org.webpieces.app.example1.business;

import java.util.List;

import javax.inject.Inject;

import org.webpieces.app.example1.remoteclients.TweetSearchService;

//public class TweetIdSearchImpl implements TweetIdSearch {
public class TweetIdSearch {
  @Inject
  private TweetSearchService tweetSearchService;

  public List<Integer> query(String queryString) {
    return tweetSearchService.query(queryString);
  }
}
