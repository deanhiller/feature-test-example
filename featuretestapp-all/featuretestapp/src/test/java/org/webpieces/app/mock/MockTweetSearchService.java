package org.webpieces.app.mock;

import java.util.Arrays;
import java.util.List;

import org.webpieces.app.example1.remoteclients.TweetSearchService;

public class MockTweetSearchService implements TweetSearchService {
  @Override
  public List<Integer> query(String queryString) {
    return Arrays.asList(1, 2, 3);
  }
}
