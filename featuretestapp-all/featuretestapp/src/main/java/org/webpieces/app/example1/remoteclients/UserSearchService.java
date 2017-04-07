package org.webpieces.app.example1.remoteclients;

import org.webpieces.app.example1.model.User;

public interface UserSearchService {
  User lookup(String userId);
}
