package org.webpieces.app.example1;

import javax.inject.Inject;

import org.webpieces.app.example1.client.RemoteHydratorService;
import org.webpieces.app.example1.model.Tweet;

public class HydratorImpl implements Hydrator {
  @Inject
  private RemoteHydratorService hydratorService;

  @Override
  public Tweet hydrate(String tweetId) {
    return hydratorService.hydrate(tweetId);
  }
}
