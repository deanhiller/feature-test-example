package org.webpieces.app.example1.mock;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import org.webpieces.app.example1.client.RemoteHydratorService;
import org.webpieces.app.example1.model.Tweet;
import org.webpieces.app.example1.model.User;

public class MockHydratorService implements RemoteHydratorService {
  private Map<String, Tweet> tweets = ImmutableMap.of(
      "a", new Tweet("a", "pstover", "tweeting"),
      "b", new Tweet("b", "dhiller", "test1"),
      "c", new Tweet("c", "dhiller", "test2"));

  @Override
  public Tweet hydrate(String tweetId) {
    return tweets.get(tweetId);
  }
}
