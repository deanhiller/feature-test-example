package org.webpieces.app.example1.business;

import javax.inject.Inject;

import org.webpieces.app.example1.model.User;
import org.webpieces.app.example1.remoteclients.UserSearchService;

//public class UserSearchImpl implements UserSearch {
public class UserSearch {
  @Inject
  private UserSearchService userSearchService;

//  @Override
  public User lookup(String userId) {
    return userSearchService.lookup(userId);
  }
}
