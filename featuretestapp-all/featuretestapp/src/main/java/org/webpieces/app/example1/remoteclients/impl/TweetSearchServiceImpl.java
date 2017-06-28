package org.webpieces.app.example1.remoteclients.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.webpieces.app.example1.model.db.TweetDbo;
import org.webpieces.app.example1.remoteclients.TweetSearchService;
import org.webpieces.plugins.hibernate.Em;

public class TweetSearchServiceImpl implements TweetSearchService {
  @Override
  public List<Integer> query(String queryString) {
    return TweetDbo.findAll(Em.get()).stream().map(TweetDbo::getId).collect(Collectors.toList());
  }
}
