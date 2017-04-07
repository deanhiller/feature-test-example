package org.webpieces.app.example1.mock;

import java.util.Arrays;
import java.util.List;

import org.webpieces.app.client.RemoteTweetSearchService;

public class MockTweetSearchService implements RemoteTweetSearchService {
  @Override
  public List<String> query(String queryString) {
    return Arrays.asList("a", "b", "c");
  }
}
