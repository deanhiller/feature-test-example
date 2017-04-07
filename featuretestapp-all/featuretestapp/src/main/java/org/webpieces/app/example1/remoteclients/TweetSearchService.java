package org.webpieces.app.example1.remoteclients;

import java.util.List;

public interface TweetSearchService {
  List<Integer> query(String queryString);
}
