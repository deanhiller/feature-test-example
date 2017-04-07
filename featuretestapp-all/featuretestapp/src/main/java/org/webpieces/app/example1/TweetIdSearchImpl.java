package org.webpieces.app.example1;

import java.util.List;
import javax.inject.Inject;

import org.webpieces.app.client.RemoteTweetSearchService;

public class TweetIdSearchImpl implements TweetIdSearch {
  @Inject
  private RemoteTweetSearchService tweetSearchService;

  @Override
  public List<String> query(String queryString) {
    return tweetSearchService.query(queryString);
  }
}
