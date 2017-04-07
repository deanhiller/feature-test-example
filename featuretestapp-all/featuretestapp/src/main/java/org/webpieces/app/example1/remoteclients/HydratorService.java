package org.webpieces.app.example1.remoteclients;

import org.webpieces.app.example1.model.Tweet;

public interface HydratorService {
  Tweet hydrate(Integer tweetId);
}
