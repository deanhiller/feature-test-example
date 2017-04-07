package org.webpieces.app.example1.business;

import javax.inject.Inject;

import org.webpieces.app.example1.remoteclients.HydratorService;
import org.webpieces.app.example1.model.Tweet;

//public class HydratorImpl implements Hydrator {
public class Hydrator {
  @Inject
  private HydratorService hydratorService;

//  @Override
  public Tweet hydrate(Integer tweetId) {
    return hydratorService.hydrate(tweetId);
  }
}
