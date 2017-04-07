package org.webpieces.app.example1;

import javax.inject.Inject;

import org.webpieces.app.example1.client.RemoteUserSearchService;
import org.webpieces.app.example1.model.User;

public class UserSearchImpl implements UserSearch {
  @Inject
  private RemoteUserSearchService userSearchService;

  @Override
  public User lookup(String userId) {
    return userSearchService.lookup(userId);
  }
}
