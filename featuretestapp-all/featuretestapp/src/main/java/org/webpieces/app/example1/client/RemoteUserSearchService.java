package org.webpieces.app.example1.client;

import org.webpieces.app.example1.model.User;

public interface RemoteUserSearchService {
  User lookup(String userId);
}
