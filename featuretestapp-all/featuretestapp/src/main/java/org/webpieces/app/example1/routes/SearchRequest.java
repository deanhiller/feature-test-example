package org.webpieces.app.example1.routes;

/**
 * Created by pstover on 4/26/17.
 */
public class SearchRequest {
  private String query;
  private int maxResults;

  public SearchRequest() {
  }

  public SearchRequest(String query, int maxResults) {
    this.query = query;

    this.maxResults = maxResults;
  }

  public String getQuery() {

    return query;
  }

  public int getMaxResults() {
    return maxResults;
  }
}
