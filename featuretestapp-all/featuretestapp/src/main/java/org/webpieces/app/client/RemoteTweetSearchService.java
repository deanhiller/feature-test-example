package org.webpieces.app.client;

import java.util.List;

public interface RemoteTweetSearchService {
  List<String> query(String queryString);
}
