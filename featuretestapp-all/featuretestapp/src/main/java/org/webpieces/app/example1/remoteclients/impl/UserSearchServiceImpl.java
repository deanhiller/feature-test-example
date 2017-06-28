package org.webpieces.app.example1.remoteclients.impl;

import org.webpieces.app.example1.model.User;
import org.webpieces.app.example1.model.db.UserDbo;
import org.webpieces.app.example1.remoteclients.UserSearchService;
import org.webpieces.plugins.hibernate.Em;

public class UserSearchServiceImpl implements UserSearchService {
  @Override
  public User lookup(String userId) {
//    UserDbo user = Em.get().find(UserDbo.class, 1);
    UserDbo user = UserDbo.findByName(Em.get(), userId);

    if (user == null)
      return null;

    return new User(
        user.getName(),
        String.format("%s %s", user.getFirstName(), user.getLastName())
    );
  }
}
