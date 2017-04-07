package org.webpieces.app.example1.routes;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.webpieces.app.example1.business.Hydrator;
import org.webpieces.app.example1.business.TweetIdSearch;
import org.webpieces.app.example1.business.UserSearch;
import org.webpieces.app.example1.model.Tweet;
import org.webpieces.app.example1.model.User;
import org.webpieces.ctx.api.Current;
import org.webpieces.httpparser.api.common.KnownHeaderName;
import org.webpieces.httpparser.api.dto.HttpResponse;
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
    return Actions.renderView("jsonIndex.html");
  }

  public Action search(String queryString) {
    Current.addModifyResponse((r)->modify(r));
    List<Integer> tweetIds = tweetIdSearch.query(queryString);

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

  private HttpResponse modify(HttpResponse r) {
    r.getHeaderLookupStruct().getHeader(KnownHeaderName.CONTENT_TYPE).setValue("application/json");

    return r;
  }
}
