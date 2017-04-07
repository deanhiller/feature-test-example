package org.webpieces.app.mock;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import org.webpieces.app.example1.remoteclients.HydratorService;
import org.webpieces.app.example1.model.Tweet;

public class MockHydratorService implements HydratorService {
  private Map<Integer, Tweet> tweets = ImmutableMap.of(
      1, new Tweet("pstover", "tweeting"),
      2, new Tweet("dhiller", "test1"),
      3, new Tweet("dhiller", "test2"));

  @Override
  public Tweet hydrate(Integer tweetId) {
    return tweets.get(tweetId);
  }
}
