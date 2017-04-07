package org.webpieces.app.example1;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.webpieces.app.example1.model.Tweet;
import org.webpieces.app.example1.model.User;
import org.webpieces.router.api.actions.Action;
import org.webpieces.router.api.actions.Actions;

@Singleton
public class JsonController {
  @Inject
  private UserSearch userSearch;

  @Inject
  private TweetIdSearch tweetIdSearch;

  @Inject
  private Hydrator hydrator;


  public Action index() {
    return Actions.renderThis();
  }

  public Action search(String queryString) {
    List<String> tweetIds = tweetIdSearch.query(queryString);

    List<Tweet> tweets = tweetIds.stream()
        .map(tweetId -> hydrator.hydrate(tweetId))
        .collect(Collectors.toList());

    List<User> users = tweets.stream()
        .map(Tweet::getFromUser)
        .distinct()
        .map(userSearch::lookup)
        .collect(Collectors.toList());

    return Actions.renderThis("tweets", tweets, "users", users);
  }
}
