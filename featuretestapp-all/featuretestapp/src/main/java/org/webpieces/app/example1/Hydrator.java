package org.webpieces.app.example1;

import org.webpieces.app.example1.model.Tweet;

public interface Hydrator {
  Tweet hydrate(String tweetId);
}
