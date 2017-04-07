package org.webpieces.app.example1.mock;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import org.webpieces.app.example1.client.RemoteUserSearchService;
import org.webpieces.app.example1.model.User;

public class MockUserSearchService implements RemoteUserSearchService {
  private Map<String, User> users = ImmutableMap.of(
        "pstover", new User("pstover", "Patrick Stover"),
        "dhiller", new User("dhiller", "Dean Hiller"));

  @Override
  public User lookup(String userId) {
    return users.get(userId);
  }
}
