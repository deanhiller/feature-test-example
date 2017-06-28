package org.webpieces.app.example1.remoteclients.impl;

import org.webpieces.app.example1.model.Tweet;
import org.webpieces.app.example1.model.db.TweetDbo;
import org.webpieces.app.example1.remoteclients.HydratorService;
import org.webpieces.plugins.hibernate.Em;

public class HydratorServiceImpl implements HydratorService {
  @Override
  public Tweet hydrate(Integer tweetId) {
    TweetDbo tweetDbo = TweetDbo.findById(Em.get(), tweetId);

    if (tweetDbo == null)
      return null;

    return new Tweet(tweetDbo.getFromUser(), tweetDbo.getText());
  }
}
