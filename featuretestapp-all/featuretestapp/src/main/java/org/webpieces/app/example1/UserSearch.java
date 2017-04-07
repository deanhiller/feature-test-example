package org.webpieces.app.example1;

import org.webpieces.app.example1.model.User;

public interface UserSearch {
  User lookup(String userId);
}
