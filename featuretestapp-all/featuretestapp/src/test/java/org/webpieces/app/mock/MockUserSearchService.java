package org.webpieces.app.mock;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import org.webpieces.app.example1.remoteclients.UserSearchService;
import org.webpieces.app.example1.model.User;

public class MockUserSearchService implements UserSearchService {
  private Map<String, User> users = ImmutableMap.of(
        "pstover", new User("pstover", "Patrick Stover"),
        "dhiller", new User("dhiller", "Dean Hiller"));

  @Override
  public User lookup(String userId) {
    return users.get(userId);
  }
}
