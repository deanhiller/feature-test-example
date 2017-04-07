package org.webpieces.app.example1.client;

import org.webpieces.app.example1.model.Tweet;

public interface RemoteHydratorService {
  public Tweet hydrate(String tweetId);
}
